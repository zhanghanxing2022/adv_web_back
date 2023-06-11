package cn.edu.fudan.advweb.backend.response;

public class UserAddAlgorithmResponse {

    private String message;

    public UserAddAlgorithmResponse() {
    }

    public UserAddAlgorithmResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
