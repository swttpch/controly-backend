package br.com.controly.dtos;

public class TopicFollowResponse {
    private Boolean userHasFollowedTopic;
    private Integer followedsTotal;

    public Boolean getUserHasFollowedTopic() {
        return userHasFollowedTopic;
    }

    public void setUserHasFollowedTopic(Boolean userHasFollowedTopic) {
        this.userHasFollowedTopic = userHasFollowedTopic;
    }

    public Integer getFollowedsTotal() {
        return followedsTotal;
    }

    public void setFollowedsTotal(Integer followedsTotal) {
        this.followedsTotal = followedsTotal;
    }
}
