package cn.edu.fudan.advweb.backend.response;

public class UserAddFigureResponse {

    private final String message;

    public UserAddFigureResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
