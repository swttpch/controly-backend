package controly.dto;

public class PostPointResponse {

    private Boolean point;
    private Long postPointTotal;


    public PostPointResponse(){}

    public Boolean getPoint() {
        return point;
    }

    public void setPoint(Boolean point) {
        this.point = point;
    }

    public Long getPostPointTotal() {
        return postPointTotal;
    }

    public void setPostPointTotal(Long postPointTotal) {
        this.postPointTotal = postPointTotal;
    }
}
