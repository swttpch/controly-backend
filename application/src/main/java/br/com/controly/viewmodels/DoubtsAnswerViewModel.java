package br.com.controly.viewmodels;

import br.com.controly.domain.entities.Answers;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DoubtsAnswerViewModel {
    private Long idAnswer;
    private boolean isSolved;
    private LocalDateTime solvedIn;
}
