package br.com.controly.viewmodels;

import br.com.controly.domain.entities.TopicEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedTopicViewModel {


    private Long idTopic;
    private String name;
    private String svg;
    private String png;

    public SimplifiedTopicViewModel convert(TopicEntity topicEntity){
        SimplifiedTopicViewModel simplifiedTopicResponse = new SimplifiedTopicViewModel();
        simplifiedTopicResponse.setIdTopic(topicEntity.getIdTopic());
        simplifiedTopicResponse.setName(topicEntity.getName());
        simplifiedTopicResponse.setSvg(topicEntity.getSvg());
        simplifiedTopicResponse.setPng(topicEntity.getPng());
        return simplifiedTopicResponse;
    }
}
