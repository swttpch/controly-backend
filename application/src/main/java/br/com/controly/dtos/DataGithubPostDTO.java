package br.com.controly.dtos;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class DataGithubPostDTO {


    @Value("${github.client_id}")
    private String CLIENT_ID;
    @Value("${github.client_id}")
    private String CLIENT_SECRET;
    private String code;

    public DataGithubPostDTO(String code) {
        this.code = code;
    }
}
