package br.com.controly.viewmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPointViewModel {
    private Boolean userHasVoted;
    private Long postPointTotal;
}
