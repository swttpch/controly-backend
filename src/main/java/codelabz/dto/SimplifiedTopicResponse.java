package codelabz.dto;

import codelabz.entity.TopicEntity;

public class SimplifiedTopicResponse {


    private Long idTopic;
    private String name;
    private String svg;

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

    public SimplifiedTopicResponse convert(TopicEntity topicEntity){
        SimplifiedTopicResponse simplifiedTopicResponse = new SimplifiedTopicResponse();
        simplifiedTopicResponse.setIdTopic(topicEntity.getIdTopic());
        simplifiedTopicResponse.setName(topicEntity.getName());
        simplifiedTopicResponse.setSvg(topicEntity.getSvg());
        return simplifiedTopicResponse;
    }
}
