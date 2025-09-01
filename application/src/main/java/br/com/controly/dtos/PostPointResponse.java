package br.com.controly.dtos;

public class PostPointResponse {



    private Boolean userHasVoted;
    private Long postPointTotal;


    public PostPointResponse(){}

    public Boolean getUserHasVoted() {
        return userHasVoted;
    }

    public void setUserHasVoted(Boolean userHasVoted) {
        this.userHasVoted = userHasVoted;
    }

    public Long getPostPointTotal() {
        return postPointTotal;
    }

    public void setPostPointTotal(Long postPointTotal) {
        this.postPointTotal = postPointTotal;
    }
}
