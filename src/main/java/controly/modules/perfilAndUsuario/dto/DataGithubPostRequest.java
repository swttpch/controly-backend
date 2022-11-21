package controly.modules.perfilAndUsuario.dto;

import controly.config.Constant;

public class DataGithubPostRequest {
    private final String client_id = Constant.CLIENT_ID;
    private final String client_secret = Constant.CLIENT_SECRET;
    private String code;

    public DataGithubPostRequest(String code) {
        this.code = code;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getCode() {
        return code;
    }
}
