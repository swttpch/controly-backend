package br.com.controly.services;

import br.com.controly.viewmodels.LikeCommentViewModel;
import br.com.controly.viewmodels.UserViewModel;
import br.com.controly.exception.CommentIdNotFould;
import br.com.controly.dtos.CreateCommentDTO;
import br.com.controly.viewmodels.SimplifiedCommentViewModel;
import br.com.controly.jpa.CommentRepository;
import br.com.controly.jpa.CommentPointsRepository;
import br.com.controly.domain.entities.Comments;
import br.com.controly.domain.entities.CommentPoints;
import br.com.controly.domain.entities.Posts;
import br.com.controly.domain.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

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

    public Comments getCommentById(Long idComment) {
        return commentRepository.findById(idComment).orElseThrow(CommentIdNotFould::new);
    }

    public SimplifiedCommentViewModel getSimplifiedComment(Comments comment) {
        if (comment == null) return null;
        UserViewModel user = userService.getSimplifiedUser(comment.getOwner());
        return new SimplifiedCommentViewModel();
    }

    @Transactional
    public SimplifiedCommentViewModel createPost(CreateCommentDTO newPost) {
        Posts post = postService.getPostById(newPost.getIdPost());
        Users user = userService.getUserById(newPost.getIdUser());
        Comments comment = commentRepository.save(new Comments());
        return getSimplifiedComment(comment);
    }

    public int deleteComment(Long idComment) {
        Comments comment = getCommentById(idComment);
        commentRepository.delete(comment);
        return 1;
    }

    public int processLikeComment(Long idComment, Long idUser) {
        Comments comment = getCommentById(idComment);
        Users user = userService.getUserById(idUser);
        if (checkIfCommentHasLikeByUser(idComment, idUser))
            return unlikeComment(comment, user);
        return likeComment(comment, user);
    }

    public int likeComment(Comments comment, Users user) {
        CommentPoints like = new CommentPoints();
        like.setComment(comment);
        like.setUser(user);
        commentPointsRepository.save(like);
        return 0;
    }

    public int unlikeComment(Comments comment, Users user) {
        CommentPoints like = commentPointsRepository.existByCommentAndUser(comment, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found"));
        commentPointsRepository.delete(like);
        return 1;
    }

    public boolean checkIfCommentHasLikeByUser(Long idComment, Long idUser) {
        Comments comment = getCommentById(idComment);
        Users user = userService.getUserById(idUser);
        return commentPointsRepository.existByCommentAndUser(comment, user).isPresent();
    }

    public LikeCommentViewModel checkIfCommentHasLikeByUserInMobile(Long idComment, Long idUser) {
        Comments comment = getCommentById(idComment);
        Users user = userService.getUserById(idUser);
        LikeCommentViewModel response = new LikeCommentViewModel();
        response.setIdComment(idComment);
        response.setIdUser(idUser);
        Integer likes = commentPointsRepository.countByCommentAndUser(comment, user);
        response.setQuantLikes(likes);
        Optional<CommentPoints> entity = commentPointsRepository.existByCommentAndUser(comment, user);
        if (entity.isPresent()) {
            response.setUserHasVoted(true);
            return response;
        }
        response.setUserHasVoted(false);
        return response;

    }




    public List<SimplifiedCommentViewModel> getAllCommentsFromPost(Long idPost) {
        Posts post = postService.getPostById(idPost);
        List<Comments> commentEntities = commentRepository.findByPost(post);
        return commentEntities.stream()
                .map(this::getSimplifiedComment).collect(Collectors.toList());
    }

    public SimplifiedCommentViewModel getSingleComment(Long idComment) {
        Comments comment = getCommentById(idComment);
        return getSimplifiedComment(comment);
    }



    public List<LikeCommentViewModel> getAllCommentsFromPostInMobile(Long idPost, Long idUser, Boolean sort) {
        Posts post = postService.getPostById(idPost);
        List<Comments> commentEntities = commentRepository.findByPost(post);
        List<LikeCommentViewModel> newList = new ArrayList<>();
        for (int i = 0; i < commentEntities.size(); i++) {

            newList.add(this.checkIfCommentHasLikeByUserInMobile(commentEntities.get(i).getId(), idUser));

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
        Comments comment = getCommentById(idComment);
        if(comment.getId()==idComment){
            Users user = userService.getUserById(idUser);
            if(user.getId()==idUser){

                response.setIdUser(idUser);
                response.setIdComment(idComment);

                if (checkIfCommentHasLikeByUser(idComment, idUser)){
                    unlikeComment(comment, user);
                    response.setUserHasVoted(false);
                }else {
                    likeComment(comment, user);
                    response.setUserHasVoted(true);
                }
                response.setQuantLikes(1);

            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment not found");
        }
        return response;
    }
}
