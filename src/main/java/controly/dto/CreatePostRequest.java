package controly.dto;

import lombok.Getter;

@Getter
public class CreatePostRequest {
    private String title;
    private String content;
    private Long idUser;
    private Long idTopic;

}
