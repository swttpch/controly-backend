package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostPointViewModel {
    private Boolean userHasVoted;
    private Long postPointTotal;
}
