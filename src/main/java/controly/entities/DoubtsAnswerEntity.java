package controly.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
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
}
