package cn.edu.fudan.advweb.backend.request;

public class UserAddImageRequest {

    private String name;

    public UserAddImageRequest() {
    }

    public UserAddImageRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
