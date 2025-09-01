package br.com.controly.viewmodels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeCommentViewModel implements Comparable<LikeCommentViewModel>{

    private Long idUser;
    private Long idComment;
    private Boolean userHasVoted;

    private Integer quantLikes;

    @Override
    public int compareTo(LikeCommentViewModel o) {
        return Integer.compare(o.quantLikes,this.quantLikes);
    }
}
