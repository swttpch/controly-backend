package br.com.controly.services;

import br.com.controly.config.LoggerConfig;
import br.com.controly.domain.entities.Topics;
import br.com.controly.domain.entities.Users;
import br.com.controly.dtos.*;
import br.com.controly.exception.PostIdNotFould;
import br.com.controly.jpa.PostPointsRepository;
import br.com.controly.jpa.PostRepository;
import br.com.controly.domain.entities.Posts;
import br.com.controly.domain.entities.PostPoints;
import br.com.controly.mappers.UserMapper;
import br.com.controly.viewmodels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

    @Autowired
    private UserMapper userMapper;

    public PostService() {
    }

    public Posts getPostById(Long idPost){
        return postRepository.findById(idPost).orElseThrow(PostIdNotFould::new);
    }

    public List<SimplifiedPostWithContentViewModel> getAllPosts(Boolean sort) {
        LoggerConfig.getLogger().info("Inside getAllPosts");
        List<Posts> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++) {

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            response.setIdPost(postEntityList.get(i).getId());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedAt());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(userMapper.toUserViewModel(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel());
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getId());

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
        List<Posts> postEntityList = postRepository.findAll();
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++){

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            Optional<PostPoints> points = postPointsRepository.existByPostAndUser(postEntityList.get(i).getId(), idUser);
            response.setIdPost(postEntityList.get(i).getId());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedAt());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(userMapper.toUserViewModel(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel());
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getId());

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

    public PostDetailedViewModel getPostDetailedDtoCommon(Posts post, List<SimplifiedCommentViewModel> comments){
        UserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new PostDetailedViewModel();
    }

    public PostDetailedViewModel getPostDetailedDtoDoubt(Posts post, List<SimplifiedCommentViewModel> comments){
        UserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        DoubtsAnswerViewModel answer = new DoubtsAnswerViewModel();
        return new PostDetailedViewModel();
    }

    @Transactional
    public PostDetailedViewModel processGetPostByID(Long id, List<SimplifiedCommentViewModel> comments){
        Posts post = getPostById(id);
        if (post.isDoubt())
            return getPostDetailedDtoDoubt(post, comments);
        return getPostDetailedDtoCommon(post, comments);
    }

    @Deprecated
    public void processSetPointForPost(Long idPost, Long idUser, int point){
        Users user = userService.getUserById(idUser);
        Posts post = getPostById(idPost);
        PostPoints points = postPointsRepository.existByPostAndUser(idPost, idUser).orElse(new PostPoints());
        points.setPoints(point);
        points.setUser(user);
        points.setPost(post);
        postPointsRepository.save(points);
    }

    public PostPointViewModel processSetPointForPostBool(Long idPost, Long idUser){

        PostPointViewModel postPointResponse = new PostPointViewModel();
        Users user = userService.getUserById(idUser);
        Posts post = getPostById(idPost);


        Optional<PostPoints> points = postPointsRepository.existByPostAndUser(idPost, idUser);
        Long total = postPointsRepository.countByPost_IdPost(idPost);

        if(points.isPresent()){
            postPointsRepository.delete(points.get());
            postPointResponse.setUserHasVoted(false);
            postPointResponse.setPostPointTotal(total-1);
            return postPointResponse;
        } else {
            PostPoints newPoint = new PostPoints();
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
        Posts post = getPostById(idPost);
        postRepository.delete(post);
        return 1;
    }

    public List<Posts> getPostByUserId(Long idUser){
        return postRepository.findByOwnerIdUser(idUser);
    }

    public SimplifiedPostViewModel getSimplifiedPost(Posts post){
        if (post == null) return null;
        UserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostViewModel();
    }

    public SimplifiedPostWithContentViewModel getSimplifiedWithContentPost(Posts post){
        if (post == null) return null;
        UserViewModel user = userService.getSimplifiedUser(post.getOwner());
        SimplifiedTopicViewModel topic = topicService.getSimplifiedTopic(post.getTopic());
        return new SimplifiedPostWithContentViewModel();
    }

    public Posts getPostMostRatedByUserId(Long idUser){
        List<Posts> posts = postRepository.findByOwnerIdUser(idUser);
        if (posts.isEmpty()) return null;
        return posts.get(0);
    }
    @Transactional
    public SimplifiedPostWithContentViewModel createPost(CreatePostDTO newPost) {
        Posts post = convertDtoToPost(newPost);
        postRepository.save(post);
        return this.getSimplifiedWithContentPost(post);
    }

    public Posts convertDtoToPost(CreatePostDTO newPost){
        Users user = userService.getUserById(newPost.getIdUser());
        Topics topic = topicService.getTopicById(newPost.getIdTopic());
        return newPost.convert(topic, user);
    }

    public Integer getUserPointsInPost(Long idPost, Long idUser){
        Posts post = getPostById(idPost);
        Users user = userService.getUserById(idUser);
        Optional<PostPoints> points = postPointsRepository.existByPostAndUser(idPost, idUser);
        return points.map(PostPoints::getPoints).orElse(0);
    }

    public List<Posts> getTopicoForPost(Long idTopic){
        return postRepository.findByTopicIdTopic(idTopic);
    }

    public List<SimplifiedPostWithContentViewModel> getTopicoForPostMobile(Long idTopic, Long idUser){

        List<Posts> postEntityList = postRepository.findByTopicIdTopic(idTopic);
        List<SimplifiedPostWithContentViewModel> newList = new ArrayList<>();

        for(int i = 0; i < postEntityList.size(); i++){

            PostPointViewModel postPointResponse = new PostPointViewModel();
            SimplifiedPostWithContentViewModel response = new SimplifiedPostWithContentViewModel();
            Optional<PostPoints> points = postPointsRepository.existByPostAndUser(postEntityList.get(i).getId(), idUser);
            response.setIdPost(postEntityList.get(i).getId());
            response.setComments(postEntityList.get(i).getComments().size());
            response.setCreatedIn(postEntityList.get(i).getCreatedAt());
            response.setContent(postEntityList.get(i).getContent());
            response.setTitle(postEntityList.get(i).getTitle());
            response.setOwner(userMapper.toUserViewModel(postEntityList.get(i).getOwner()));
            response.setDoubt(postEntityList.get(i).isDoubt());
            response.setTopic(new SimplifiedTopicViewModel());
            Long total = postPointsRepository.countByPost_IdPost(postEntityList.get(i).getId());

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


    public Page<Posts> getAllPostsPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Posts> postEntityPage = postRepository.findAll(pageable);
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
