package controly.service;

import controly.dto.*;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import controly.exception.PostIdNotFould;
import controly.mapper.PostMapper;
import controly.repository.UserRepository;
import controly.entity.PostPointsEntity;
import controly.entity.PostEntity;
import controly.repository.PostPointsRepository;
import controly.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private ValidationService validation;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostPointsRepository postPointsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostMapper postMapper;

    public PostService() {
    }

    public PostEntity getPostById(Long idPost){
        return postRepository.findById(idPost).orElseThrow(PostIdNotFould::new);
    }

    public List<SimplifiedPostWithContentResponse> getAllPosts() {
        List<PostEntity> postEntityList = postRepository.findAll();
        if (postEntityList.isEmpty()) return null;
        return postEntityList.stream()
                .map(this::getSimplifiedWithContentPost)
                .collect(Collectors.toList());
    }

    public PostDetailedResponse getPostDetailedDtoCommun(PostEntity post, List<SimplifiedCommentResponse> comments){
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
        return getPostDetailedDtoCommun(post, comments);
    }

    public void processSetPointForPost(Long idPost, Long idUser, int point){
        UserEntity user = userService.getUserById(idUser);
        PostEntity post = getPostById(idPost);
        PostPointsEntity points = postPointsRepository.existByPostAndUser(idPost, idUser).orElse(new PostPointsEntity());
        points.setPoints(point);
        points.setUser(user);
        points.setPost(post);
        postPointsRepository.save(points);
    }
    @Transactional
    public int deletePost(Long idPostagem) {
        PostEntity postagem = getPostById(idPostagem);
        postRepository.delete(postagem);
        return 1;
    }

    @Transactional
    public ResponseEntity<List<PostEntity>> getPostagemByIdUser(Long idUser){
        if (validation.existsUsuario(idUser)) return ResponseEntity.status(404).build();

        List<PostEntity> postEntityList = postRepository.findByOwnerIdUser(idUser);

        return postEntityList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(postEntityList);
    }

    public List<PostEntity> getPostByUserId(Long idUser){
        return postRepository.findByOwnerIdUser(idUser);
    }

    @Transactional
    public ResponseEntity<PostPointsEntity> findPontuacaoPostagem(Long postagem, Long usuario, int pontuacao){
        PostPointsEntity postPointsEntity = postPointsRepository.findByPostIdPostAndUserIdUser(postagem, usuario).get();
        postPointsEntity.setPoints(pontuacao);
        return  ResponseEntity.status(200).body(postPointsEntity);
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
                .max((post1, post2) -> Integer.compare(post1.getPoints(), post2.getPoints())).get();
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
}
