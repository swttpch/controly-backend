package controly.service;

import controly.dto.*;
import controly.entity.PostEntity;
import controly.entity.UserEntity;
import controly.entity.CommentEntity;
import controly.entity.CommentPointsEntity;
import controly.exception.CommentIdNotFould;
import controly.repository.CommentRepository;
import controly.repository.CommentPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentPointsRepository commentPointsRepository;

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public CommentService() {
    }

    public CommentEntity getCommentById(Long idComment){
        return commentRepository.findById(idComment).orElseThrow(CommentIdNotFould::new);
    }

    public SimplifiedCommentResponse getSimplifiedComment(CommentEntity comment){
        if (comment==null) return null;
        SimplifiedUserResponse user = userService.getSimplifiedUser(comment.getOwner());
        return new SimplifiedCommentResponse(comment, user);
    }
    @Transactional
    public SimplifiedCommentResponse createPost(CreateCommentRequest newPost) {
        PostEntity post = postService.getPostById(newPost.getIdPost());
        UserEntity user = userService.getUserById(newPost.getIdUser());
        CommentEntity comment = commentRepository.save(newPost.convert(post,user));
        return getSimplifiedComment(comment);
    }

    public int deleteComment(Long idComment) {
        CommentEntity comment = getCommentById(idComment);
        commentRepository.delete(comment);
        return 1;
    }

    public int processLikeComment(Long idComment, Long idUser){
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        if (checkIfCommentHasLikeByUser(idComment, idUser))
            return unlikeComment(comment, user);
        return likeComment(comment,user);
    }

    public int likeComment(CommentEntity comment, UserEntity user){
        CommentPointsEntity like = new CommentPointsEntity();
        like.setComment(comment);
        like.setUser(user);
        commentPointsRepository.save(like);
        return 0;
    }

    public int unlikeComment(CommentEntity comment, UserEntity user){
        CommentPointsEntity like = commentPointsRepository.existByCommentAndUser(comment, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not fould"));
        commentPointsRepository.delete(like);
        return 1;
    }

    public boolean checkIfCommentHasLikeByUser(Long idComment, Long idUser){
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        return commentPointsRepository.existByCommentAndUser(comment, user).isPresent();
    }

    public List<SimplifiedCommentResponse> getAllCommentsFromPost(Long idPostagem) {
        PostEntity post = postService.getPostById(idPostagem);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        return commentEntities.stream()
                .map(this::getSimplifiedComment).collect(Collectors.toList());
    }
}
