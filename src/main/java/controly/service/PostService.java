package controly.service;

import controly.config.LoggerConfig;
import controly.dto.*;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import controly.exception.PostIdNotFould;
import controly.entity.PostPointsEntity;
import controly.entity.PostEntity;
import controly.repository.PostPointsRepository;
import controly.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostPointsRepository postPointsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    public PostService() {
    }

    public PostEntity getPostById(Long idPost){
        return postRepository.findById(idPost).orElseThrow(PostIdNotFould::new);
    }

    public List<SimplifiedPostWithContentResponse> getAllPosts() {
        LoggerConfig.getLogger().info("Inside getAllPosts");
        List<PostEntity> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentResponse> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++) {

            PostPointResponse postPointResponse = new PostPointResponse();
            SimplifiedPostWithContentResponse response = new SimplifiedPostWithContentResponse();
            response.setIdPost(postEntityList.get(i).getIdPost());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedIn());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(new SimplifiedUserResponse().convert(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicResponse().convert(postEntityList.get(i).getTopic()));
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getIdPost());

            if (total != null) {
                response.setPoints(Math.toIntExact(total));
            } else {
                response.setPoints(0);
            }
            response.setUserHasVoted(false);


            newList.add(response);
        }
        return newList;
    }

    public List<SimplifiedPostWithContentResponse> getAllPosts(Long idUser) {
        LoggerConfig.getLogger().info("Inside getAllPosts whith idUser: "+idUser);
        List<PostEntity> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentResponse> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++){

            PostPointResponse postPointResponse = new PostPointResponse();
            SimplifiedPostWithContentResponse response = new SimplifiedPostWithContentResponse();
            Optional<PostPointsEntity> points = postPointsRepository.existByPostAndUser(postEntityList.get(i).getIdPost(), idUser);
            response.setIdPost(postEntityList.get(i).getIdPost());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedIn());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(new SimplifiedUserResponse().convert(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicResponse().convert(postEntityList.get(i).getTopic()));
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getIdPost());

            if(total!=null){
                response.setPoints(Math.toIntExact(total));
            } else {
                response.setPoints(0);
            }
            if(points.isPresent()){
                response.setUserHasVoted(true);
            } else {
                response.setUserHasVoted(false);
            }


            newList.add(response);

        }


        return newList;
    }

    public PostDetailedResponse getPostDetailedDtoCommon(PostEntity post, List<SimplifiedCommentResponse> comments){
        SimplifiedUserResponse user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicResponse topic = topicService.getSimplifiedTopic(post.getTopic());
        return new PostDetailedResponse(post, topic, user, comments);
    }

    public PostDetailedResponse getPostDetailedDtoDoubt(PostEntity post, List<SimplifiedCommentResponse> comments){
        SimplifiedUserResponse user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicResponse topic = topicService.getSimplifiedTopic(post.getTopic());
        DoubtsAnswerResponse answer = new DoubtsAnswerResponse(post.getDoubtsAnswerEntity());
        return new PostDetailedResponse(post, topic, user, comments, answer);
    }

    @Transactional
    public PostDetailedResponse processGetPostByID(Long id, List<SimplifiedCommentResponse> comments){
        PostEntity post = getPostById(id);
        if (post.isDoubt())
            return getPostDetailedDtoDoubt(post, comments);
        return getPostDetailedDtoCommon(post, comments);
    }

    @Deprecated
    public void processSetPointForPost(Long idPost, Long idUser, int point){
        UserEntity user = userService.getUserById(idUser);
        PostEntity post = getPostById(idPost);
        PostPointsEntity points = postPointsRepository.existByPostAndUser(idPost, idUser).orElse(new PostPointsEntity());
        points.setPoints(point);
        points.setUser(user);
        points.setPost(post);
        postPointsRepository.save(points);
    }

    public PostPointResponse processSetPointForPostBool(Long idPost, Long idUser){

        PostPointResponse postPointResponse = new PostPointResponse();
        UserEntity user = userService.getUserById(idUser);
        PostEntity post = getPostById(idPost);


        Optional<PostPointsEntity> points = postPointsRepository.existByPostAndUser(idPost, idUser);
        Long total = postPointsRepository.countByPost_IdPost(idPost);

        if(points.isPresent()){
            postPointsRepository.delete(points.get());
            postPointResponse.setUserHasVoted(false);
            postPointResponse.setPostPointTotal(total-1);
            return postPointResponse;
        } else {
            PostPointsEntity newPoint = new PostPointsEntity();
            newPoint.setPoints(1);
            newPoint.setUser(user);
            newPoint.setPost(post);
            postPointsRepository.save(newPoint);
            postPointResponse.setUserHasVoted(true);
            postPointResponse.setPostPointTotal(total+1);
            return postPointResponse;
        }
    }
    @Transactional
    public int deletePost(Long idPost) {
        PostEntity post = getPostById(idPost);
        postRepository.delete(post);
        return 1;
    }

    public List<PostEntity> getPostByUserId(Long idUser){
        return postRepository.findByOwnerIdUser(idUser);
    }

    public SimplifiedPostResponse getSimplifiedPost(PostEntity post){
        if (post == null) return null;
        SimplifiedUserResponse user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicResponse topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostResponse(post, topic, user);
    }

    public SimplifiedPostWithContentResponse getSimplifiedWithContentPost(PostEntity post){
        if (post == null) return null;
        SimplifiedUserResponse user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicResponse topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostWithContentResponse(post, topic, user);
    }

    public PostEntity getPostMostRatedByUserId(Long idUser){
        List<PostEntity> posts = postRepository.findByOwnerIdUser(idUser);
        if (posts.isEmpty()) return null;
        return posts.stream()
                .max(Comparator.comparingInt(PostEntity::getPoints)).get();
    }
    @Transactional
    public SimplifiedPostWithContentResponse createPost(CreatePostRequest newPost) {
        PostEntity post = convertDtoToPost(newPost);
        postRepository.save(post);
        return this.getSimplifiedWithContentPost(post);
    }

    public PostEntity convertDtoToPost(CreatePostRequest newPost){
        UserEntity user = userService.getUserById(newPost.getIdUser());
        TopicEntity topic = topicService.getTopicById(newPost.getIdTopic());
        return newPost.convert(topic, user);
    }

    public Integer getUserPointsInPost(Long idPost, Long idUser){
        PostEntity post = getPostById(idPost);
        UserEntity user = userService.getUserById(idUser);
        Optional<PostPointsEntity> points = postPointsRepository.existByPostAndUser(idPost, idUser);
        return points.map(PostPointsEntity::getPoints).orElse(0);
    }

    public List<PostEntity> getTopicoForPost(Long idTopic){
        return postRepository.findByTopicIdTopic(idTopic);
    }

    public Page<PostEntity> getAllPostsPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);
        if (!postEntityPage.hasContent()) return null;
        return postEntityPage;
    }
}
