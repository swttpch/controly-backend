package br.com.controly.viewmodels;

import br.com.controly.domain.entities.DoubtsAnswerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DoubtsAnswerViewModel {
    private Long idAnswer;
    private boolean isSolved;
    private LocalDateTime solvedIn;

    public DoubtsAnswerViewModel(DoubtsAnswerEntity doubtsAnswer) {
        convert(doubtsAnswer);
    }

    public DoubtsAnswerViewModel convert(DoubtsAnswerEntity doubtsAnswer){
        if (doubtsAnswer.getAnswer() == null) {
            return this;
        }
        setIdAnswer(doubtsAnswer.getAnswer().getIdComment());
        setSolved(doubtsAnswer.isSolved());
        setSolvedIn(doubtsAnswer.getSolvedIn());
        return this;
    }
}
