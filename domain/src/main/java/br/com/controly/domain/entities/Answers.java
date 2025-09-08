package br.com.controly.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Embeddable
@Data
public class Answers {
    @OneToOne()
    @JoinColumn(name = "answer_id", referencedColumnName = "id", table = "answers")
    private Comments answer;

    @Column(table = "answers")
    private boolean isSolved = false;

    @Column( table = "answers")
    private LocalDateTime solvedIn;
}
