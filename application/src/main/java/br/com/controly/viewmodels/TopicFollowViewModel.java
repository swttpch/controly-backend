package br.com.controly.viewmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicFollowViewModel {
    private Boolean userHasFollowedTopic;
    private Integer followedsTotal;
}
