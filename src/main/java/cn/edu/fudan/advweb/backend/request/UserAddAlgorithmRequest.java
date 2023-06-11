package cn.edu.fudan.advweb.backend.request;

import cn.edu.fudan.advweb.backend.entity.UserAlgorithmType;

public class UserAddAlgorithmRequest {

    private String algorithm;
    private UserAlgorithmType type;

    public UserAddAlgorithmRequest() {
    }

    public UserAddAlgorithmRequest(String algorithm, UserAlgorithmType type) {
        this.algorithm = algorithm;
        this.type = type;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public UserAlgorithmType getType() {
        return type;
    }

    public void setType(UserAlgorithmType type) {
        this.type = type;
    }
}
