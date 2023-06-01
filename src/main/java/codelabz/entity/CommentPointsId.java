package codelabz.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CommentPointsId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idComment;
    private Long idUser;


    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idComment == null) ? 0 : idComment.hashCode());
        result = prime * result
                + ((idUser == null) ? 0 : idUser.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommentPointsId other = (CommentPointsId) obj;
        return Objects.equals(getIdComment(), other.getIdComment()) && Objects.equals(getIdUser(), other.getIdUser());
    }
}
