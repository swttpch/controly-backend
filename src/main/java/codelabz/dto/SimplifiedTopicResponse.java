package codelabz.dto;

import codelabz.entity.TopicEntity;

public class SimplifiedTopicResponse {


    private Long idTopic;
    private String name;
    private String svg;
    private String png;

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
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

    public SimplifiedTopicResponse convert(TopicEntity topicEntity){
        SimplifiedTopicResponse simplifiedTopicResponse = new SimplifiedTopicResponse();
        simplifiedTopicResponse.setIdTopic(topicEntity.getIdTopic());
        simplifiedTopicResponse.setName(topicEntity.getName());
        simplifiedTopicResponse.setSvg(topicEntity.getSvg());
        simplifiedTopicResponse.setPng(topicEntity.getPng());
        return simplifiedTopicResponse;
    }
}
