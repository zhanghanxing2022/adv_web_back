package cn.edu.fudan.advweb.backend.response;

public class UserAddImageResponse {

    private final String message;

    public UserAddImageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
