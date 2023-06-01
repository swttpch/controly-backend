package codelabz.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DoubtsAnswerEntity {

    @OneToOne()
    @JoinColumn(name = "idAnswer", referencedColumnName = "idComment", table = "tbDoubtsAnswer")
    private CommentEntity answer;
    @Column(name = "isSolved",  table = "tbDoubtsAnswer")
    private boolean isSolved;

    @Column(name = "solvedIn", table = "tbDoubtsAnswer")
    private LocalDateTime solvedIn;

    public DoubtsAnswerEntity setSolved(boolean solved){
        this.isSolved = solved;
        return this;
    }

    public DoubtsAnswerEntity setAnswer(CommentEntity answer){
        this.answer = answer;
        return this;
    }

    public CommentEntity getAnswer() {
        return answer;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public LocalDateTime getSolvedIn() {
        return solvedIn;
    }

    public void setSolvedIn(LocalDateTime solvedIn) {
        this.solvedIn = solvedIn;
    }
}
