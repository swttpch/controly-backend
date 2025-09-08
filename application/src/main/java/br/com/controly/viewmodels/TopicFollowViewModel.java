package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicFollowViewModel {
    private Boolean userHasFollowedTopic;
    private Integer followedsTotal;
}
