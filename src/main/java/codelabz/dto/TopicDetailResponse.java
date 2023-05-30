package codelabz.dto;

import codelabz.entity.TopicEntity;

import java.time.LocalDate;

public class TopicDetailResponse {



    private Long idTopic = new TopicEntity().getIdTopic();

    private String name = new TopicEntity().getName();

    private String about = new TopicEntity().getAbout();
    private LocalDate createdAt = new TopicEntity().getCreatedAt();
    private String svg = new TopicEntity().getSvg();

    private String png = new TopicEntity().getPng();

    private Integer countFollowers;


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

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


    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
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
