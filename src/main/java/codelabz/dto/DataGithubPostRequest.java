package codelabz.dto;

public class DataGithubPostRequest {


    public static final String CLIENT_ID = "9459c70a40c43a0f6736";
    public static final String CLIENT_SECRET = "fda8ebc347ab48a541164adaa70c36edba001cc7";
    private String code;

    public DataGithubPostRequest(String code) {
        this.code = code;
    }

    public String getClient_id() {
        return CLIENT_ID;
    }

    public String getClient_secret() {
        return CLIENT_SECRET;
    }

    public String getCode() {
        return code;
    }
}
