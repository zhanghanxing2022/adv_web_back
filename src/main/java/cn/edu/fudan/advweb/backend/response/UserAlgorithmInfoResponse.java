package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.UserAlgorithmInfo;

import java.util.List;

public class UserAlgorithmInfoResponse {

    List<UserAlgorithmInfo> infos;

    public UserAlgorithmInfoResponse() {
    }

    public UserAlgorithmInfoResponse(List<UserAlgorithmInfo> infos) {
        this.infos = infos;
    }

    public List<UserAlgorithmInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<UserAlgorithmInfo> infos) {
        this.infos = infos;
    }
}
