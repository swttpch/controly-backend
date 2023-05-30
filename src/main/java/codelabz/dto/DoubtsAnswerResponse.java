package codelabz.dto;

import codelabz.entity.DoubtsAnswerEntity;

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
        if (doubtsAnswer.getAnswer() == null) {
            return this;
        }
        setIdAnswer(doubtsAnswer.getAnswer().getIdComment());
        setSolved(doubtsAnswer.isSolved());
        setSolvedIn(doubtsAnswer.getSolvedIn());
        return this;
    }
}
