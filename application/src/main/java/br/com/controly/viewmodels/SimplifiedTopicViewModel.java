package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Topics;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimplifiedTopicViewModel {
    private Long idTopic;
    private String name;
    private String svg;
    private String png;
}
