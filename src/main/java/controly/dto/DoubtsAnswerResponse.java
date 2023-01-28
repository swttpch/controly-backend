package controly.dto;

import controly.entity.CommentEntity;
import controly.entity.DoubtsAnswerEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class DoubtsAnswerResponse {
    private Long idAnswer;
    private boolean isSolved;
    private LocalDateTime solvedIn;

    public DoubtsAnswerResponse(DoubtsAnswerEntity doubtsAnswer) {
        convert(doubtsAnswer);
    }

    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public LocalDateTime getSolvedIn() {
        return solvedIn;
    }

    public void setSolvedIn(LocalDateTime solvedIn) {
        this.solvedIn = solvedIn;
    }

    public DoubtsAnswerResponse convert(DoubtsAnswerEntity doubtsAnswer){
        setIdAnswer(doubtsAnswer.getAnswer().getIdComment());
        setSolved(doubtsAnswer.isSolved());
        setSolvedIn(doubtsAnswer.getSolvedIn());
        return this;
    }
}
