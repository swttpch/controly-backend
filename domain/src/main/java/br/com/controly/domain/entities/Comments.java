package br.com.controly.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Comments extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Posts post;

    @OneToMany(mappedBy = "comment",cascade=CascadeType.ALL)
    private Set<CommentPoints> likes = new HashSet<>();

    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users owner;
}
