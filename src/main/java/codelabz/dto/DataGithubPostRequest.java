package codelabz.dto;

public class DataGithubPostRequest {


    public static final String CLIENT_ID = "47ef8a50aecc707614f3";
    public static final String CLIENT_SECRET = "9347ca50f180d85e751b361e4ea01e8472c594f9";
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
