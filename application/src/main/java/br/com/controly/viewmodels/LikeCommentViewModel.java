package br.com.controly.viewmodels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
