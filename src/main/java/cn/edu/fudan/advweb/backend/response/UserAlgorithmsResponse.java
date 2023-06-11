package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.UserAlgorithm;

import java.util.List;

public class UserAlgorithmsResponse {

    List<UserAlgorithm> userAlgorithmList;

    public UserAlgorithmsResponse() {
    }

    public UserAlgorithmsResponse(List<UserAlgorithm> userAlgorithmList) {
        this.userAlgorithmList = userAlgorithmList;
    }

    public List<UserAlgorithm> getUserAlgorithmList() {
        return userAlgorithmList;
    }

    public void setUserAlgorithmList(List<UserAlgorithm> userAlgorithmList) {
        this.userAlgorithmList = userAlgorithmList;
    }
}
