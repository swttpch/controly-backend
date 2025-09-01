package br.com.controly.dtos;

public class LikeCommentResponse implements Comparable<LikeCommentResponse>{

    private Long idUser;
    private Long idComment;
    private Boolean userHasVoted;

    private Integer quantLikes;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Boolean getUserHasVoted() {
        return userHasVoted;
    }

    public void setUserHasVoted(Boolean userHasVoted) {
        this.userHasVoted = userHasVoted;
    }

    public Integer getQuantLikes() {
        return quantLikes;
    }

    public void setQuantLikes(Integer quantLikes) {
        this.quantLikes = quantLikes;
    }

    @Override
    public int compareTo(LikeCommentResponse o) {
        return Integer.compare(o.quantLikes,this.quantLikes);
    }
}
