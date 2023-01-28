package controly.dto;

import controly.entity.PostEntity;

public class SimplifiedTopicResponse {
    private Long idTopic;
    private String name;

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
}
