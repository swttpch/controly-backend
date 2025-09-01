package br.com.controly.services;

import br.com.controly.config.LoggerConfig;
import br.com.controly.domain.entities.TopicEntity;
import br.com.controly.domain.entities.UserEntity;
import br.com.controly.dtos.*;
import br.com.controly.exception.PostIdNotFould;
import br.com.controly.jpa.PostPointsRepository;
import br.com.controly.jpa.PostRepository;
import br.com.controly.domain.entities.PostEntity;
import br.com.controly.domain.entities.PostPointsEntity;
import br.com.controly.viewmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    public List<SimplifiedPostWithContentViewModel> getAllPosts(Boolean sort) {
        LoggerConfig.getLogger().info("Inside getAllPosts");
        List<PostEntity> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++) {

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            response.setIdPost(postEntityList.get(i).getIdPost());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedIn());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(new SimplifiedUserViewModel().convert(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel().convert(postEntityList.get(i).getTopic()));
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getIdPost());

            if (total != null) {
                response.setPoints(Math.toIntExact(total));
            } else {
                response.setPoints(0);
            }
            response.setUserHasVoted(false);


            newList.add(response);
        }
        return this.sortPosts(newList,sort);
    }

    public List<SimplifiedPostWithContentViewModel> getAllPosts(Long idUser, Boolean sort) {
        LoggerConfig.getLogger().info("Inside getAllPosts whith idUser: "+idUser);
        List<PostEntity> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++){

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            Optional<PostPointsEntity> points = postPointsRepository.existByPostAndUser(postEntityList.get(i).getIdPost(), idUser);
            response.setIdPost(postEntityList.get(i).getIdPost());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedIn());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(new SimplifiedUserViewModel().convert(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel().convert(postEntityList.get(i).getTopic()));
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


        return this.sortPosts(newList,sort);
    }

    public PostDetailedViewModel getPostDetailedDtoCommon(PostEntity post, List<SimplifiedCommentViewModel> comments){
        SimplifiedUserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new PostDetailedViewModel(post, topic, user, comments);
    }

    public PostDetailedViewModel getPostDetailedDtoDoubt(PostEntity post, List<SimplifiedCommentViewModel> comments){
        SimplifiedUserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        DoubtsAnswerViewModel answer = new DoubtsAnswerViewModel(post.getDoubtsAnswerEntity());
        return new PostDetailedViewModel(post, topic, user, comments, answer);
    }

    @Transactional
    public PostDetailedViewModel processGetPostByID(Long id, List<SimplifiedCommentViewModel> comments){
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

    public PostPointViewModel processSetPointForPostBool(Long idPost, Long idUser){

        PostPointViewModel postPointResponse = new PostPointViewModel();
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

    public SimplifiedPostViewModel getSimplifiedPost(PostEntity post){
        if (post == null) return null;
        SimplifiedUserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostViewModel(post, topic, user);
    }

    public SimplifiedPostWithContentViewModel getSimplifiedWithContentPost(PostEntity post){
        if (post == null) return null;
        SimplifiedUserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostWithContentViewModel(post, topic, user);
    }

    public PostEntity getPostMostRatedByUserId(Long idUser){
        List<PostEntity> posts = postRepository.findByOwnerIdUser(idUser);
        if (posts.isEmpty()) return null;
        return posts.stream()
                .max(Comparator.comparingInt(PostEntity::getPoints)).get();
    }
    @Transactional
    public SimplifiedPostWithContentViewModel createPost(CreatePostDTO newPost) {
        PostEntity post = convertDtoToPost(newPost);
        postRepository.save(post);
        return this.getSimplifiedWithContentPost(post);
    }

    public PostEntity convertDtoToPost(CreatePostDTO newPost){
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

    public List<SimplifiedPostWithContentViewModel> getTopicoForPostMobile(Long idTopic, Long idUser){

        List<PostEntity> postEntityList = postRepository.findByTopicIdTopic(idTopic);
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++){

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            Optional<PostPointsEntity> points = postPointsRepository.existByPostAndUser(postEntityList.get(i).getIdPost(), idUser);
            response.setIdPost(postEntityList.get(i).getIdPost());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedIn());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(new SimplifiedUserViewModel().convert(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel().convert(postEntityList.get(i).getTopic()));
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


    public Page<PostEntity> getAllPostsPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<PostEntity> postEntityPage = postRepository.findAll(pageable);
        if (!postEntityPage.hasContent()) return null;
        return postEntityPage;
    }

    private List<SimplifiedPostWithContentViewModel> sortPosts(List<SimplifiedPostWithContentViewModel> lista,
                                                               Boolean sort){
        if(sort){
            Collections.sort(lista);
            return lista;
        } else {
            return lista;
        }

    }
}
