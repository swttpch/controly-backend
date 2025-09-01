package br.com.controly.services;

import br.com.controly.viewmodels.LikeCommentViewModel;
import br.com.controly.viewmodels.SimplifiedUserViewModel;
import br.com.controly.exception.CommentIdNotFould;
import br.com.controly.dtos.CreateCommentDTO;
import br.com.controly.viewmodels.SimplifiedCommentViewModel;
import br.com.controly.jpa.CommentRepository;
import br.com.controly.jpa.CommentPointsRepository;
import br.com.controly.domain.entities.CommentEntity;
import br.com.controly.domain.entities.CommentPointsEntity;
import br.com.controly.domain.entities.PostEntity;
import br.com.controly.domain.entities.UserEntity;
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

    public SimplifiedCommentViewModel getSimplifiedComment(CommentEntity comment) {
        if (comment == null) return null;
        SimplifiedUserViewModel user = userService.getSimplifiedUser(comment.getOwner());
        return new SimplifiedCommentViewModel(comment, user);
    }

    @Transactional
    public SimplifiedCommentViewModel createPost(CreateCommentDTO newPost) {
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

    public LikeCommentViewModel checkIfCommentHasLikeByUserInMobile(Long idComment, Long idUser) {
        CommentEntity comment = getCommentById(idComment);
        UserEntity user = userService.getUserById(idUser);
        LikeCommentViewModel response = new LikeCommentViewModel();
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




    public List<SimplifiedCommentViewModel> getAllCommentsFromPost(Long idPost) {
        PostEntity post = postService.getPostById(idPost);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        return commentEntities.stream()
                .map(this::getSimplifiedComment).collect(Collectors.toList());
    }

    public SimplifiedCommentViewModel getSingleComment(Long idComment) {
        CommentEntity comment = getCommentById(idComment);
        return getSimplifiedComment(comment);
    }



    public List<LikeCommentViewModel> getAllCommentsFromPostInMobile(Long idPost, Long idUser, Boolean sort) {
        PostEntity post = postService.getPostById(idPost);
        List<CommentEntity> commentEntities = commentRepository.findByPost(post);
        List<LikeCommentViewModel> newList = new ArrayList<>();
        for (int i = 0; i < commentEntities.size(); i++) {

            newList.add(this.checkIfCommentHasLikeByUserInMobile(commentEntities.get(i).getIdComment(), idUser));

        }

        return this.sortComments(newList,sort);
    }

    private List<LikeCommentViewModel> sortComments(List<LikeCommentViewModel> lista,
                                                    Boolean sort){
        if(sort){
            Collections.sort(lista);
            return lista;
        } else {
            return lista;
        }

    }

    public LikeCommentViewModel processLikeCommentInMobile(Long idComment, Long idUser) {
        LikeCommentViewModel response = new LikeCommentViewModel();
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
