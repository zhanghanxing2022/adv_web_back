package cn.edu.fudan.advweb.backend.response;

public class UserChpwdResponse {

    private final String message;

    public UserChpwdResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
