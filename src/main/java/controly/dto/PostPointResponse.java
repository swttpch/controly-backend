package controly.dto;

import controly.entity.PostEntity;
import controly.entity.UserEntity;

public class PostPointResponse {


    private PostEntity post;
    private UserEntity user;
    private Boolean point;

    public PostPointResponse(PostEntity post, UserEntity user, Boolean point) {
        this.post = post;
        this.user = user;
        this.point = point;
    }

    public PostPointResponse(){}

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Boolean getPoint() {
        return point;
    }

    public void setPoint(Boolean point) {
        this.point = point;
    }
}
