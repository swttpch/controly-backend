package controly.dto;

import controly.entity.TopicEntity;

public class TopicDetailResponse {

    private Long idTopic = new TopicEntity().getIdTopic();

    private String name = new TopicEntity().getName();

    private String about = new TopicEntity().getAbout();

    private Integer countFollowers;

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getCountFollowers() {
        return countFollowers;
    }

    public void setCountFollowers(Integer countFollowers) {
        this.countFollowers = countFollowers;
    }
}
