package codelabz.service;

import codelabz.dto.*;
import codelabz.entity.PostEntity;
import codelabz.entity.UserEntity;
import codelabz.entity.CommentEntity;
import codelabz.entity.CommentPointsEntity;
import codelabz.exception.CommentIdNotFould;
import codelabz.repository.CommentRepository;
import codelabz.repository.CommentPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    public CommentEntity getCommentById(Long idComment) {
        return commentRepository.findById(idComment).orElseThrow(CommentIdNotFould::new);
    }

    public SimplifiedCommentResponse getSimplifiedComment(CommentEntity comment) {
        if (comment == null) return null;
        SimplifiedUserResponse user = userService.getSimplifiedUser(comment.getOwner());
        return new SimplifiedCommentResponse(comment, user);
    }

    @Transactional
    public SimplifiedCommentResponse createPost(CreateCommentRequest newPost) {
        PostEntity post = postService.getPostById(newPost.getIdPost());
        UserEntity user = userService.getUserById(newPost.getIdUser());
        CommentEntity comment = commentRepository.save(newPost.convert(post, user));
        return getSimplifiedComment(comment);
    }

    public int deleteComment(Long idComment) {
        CommentEntity comment = getCommentById(idComment);
        commentRepository.delete(comment);
        return 1;
    }

    public int processLikeComment(Long idComment, Long idUser) {
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        if (checkIfCommentHasLikeByUser(idComment, idUser))
            return unlikeComment(comment, user);
        return likeComment(comment, user);
    }

    public int likeComment(CommentEntity comment, UserEntity user) {
        CommentPointsEntity like = new CommentPointsEntity();
        like.setComment(comment);
        like.setUser(user);
        commentPointsRepository.save(like);
        return 0;
    }

    public int unlikeComment(CommentEntity comment, UserEntity user) {
        CommentPointsEntity like = commentPointsRepository.existByCommentAndUser(comment, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found"));
        commentPointsRepository.delete(like);
        return 1;
    }

    public boolean checkIfCommentHasLikeByUser(Long idComment, Long idUser) {
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        return commentPointsRepository.existByCommentAndUser(comment, user).isPresent();
    }

    public LikeCommentResponse checkIfCommentHasLikeByUserInMobile(Long idComment, Long idUser) {
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        LikeCommentResponse response = new LikeCommentResponse();
        response.setIdComment(idComment);
        response.setIdUser(idUser);
        Integer likes = commentPointsRepository.countByCommentAndUser(comment, user);
        response.setQuantLikes(likes);
        Optional<CommentPointsEntity> entity = commentPointsRepository.existByCommentAndUser(comment, user);
        if (entity.isPresent()) {
            response.setUserHasVoted(true);
            return response;
        }
        response.setUserHasVoted(false);
        return response;

    }




    public List<SimplifiedCommentResponse> getAllCommentsFromPost(Long idPost) {
        PostEntity post = postService.getPostById(idPost);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        return commentEntities.stream()
                .map(this::getSimplifiedComment).collect(Collectors.toList());
    }

    public SimplifiedCommentResponse getSingleComment(Long idComment) {
        CommentEntity comment = getCommentById(idComment);
        return getSimplifiedComment(comment);
    }



    public List<LikeCommentResponse> getAllCommentsFromPostInMobile(Long idPost, Long idUser, Boolean sort) {
        PostEntity post = postService.getPostById(idPost);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        List<LikeCommentResponse> newList = new ArrayList<>();
        for (int i = 0; i < commentEntities.size(); i++) {

            newList.add(this.checkIfCommentHasLikeByUserInMobile(commentEntities.get(i).getIdComment(), idUser));

        }

        return this.sortComments(newList,sort);
    }

    private List<LikeCommentResponse> sortComments(List<LikeCommentResponse> lista,
                                                   Boolean sort){
        if(sort){
            Collections.sort(lista);
            return lista;
        } else {
            return lista;
        }

    }

    public LikeCommentResponse processLikeCommentInMobile(Long idComment, Long idUser) {
        LikeCommentResponse response = new LikeCommentResponse();
        CommentEntity comment = getCommentById(idComment);
        if(comment.getIdComment()==idComment){
            UserEntity user = userService.getUserById(idUser);
            if(user.getIdUser()==idUser){

                response.setIdUser(idUser);
                response.setIdComment(idComment);

                if (checkIfCommentHasLikeByUser(idComment, idUser)){
                    unlikeComment(comment, user);
                    response.setUserHasVoted(false);
                }else {
                    likeComment(comment, user);
                    response.setUserHasVoted(true);
                }
                response.setQuantLikes(comment.getLikes());

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment not found");
        }
        return response;
    }
}
