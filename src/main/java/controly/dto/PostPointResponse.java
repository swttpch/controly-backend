package controly.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
