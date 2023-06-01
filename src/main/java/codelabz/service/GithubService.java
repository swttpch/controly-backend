package codelabz.service;

import codelabz.dto.DataGithubPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {
    private final RestTemplate restTemplate;
    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String consumeApi(String url, DataGithubPostRequest data){
        return restTemplate.postForObject(url, data, String.class);
    }
}
