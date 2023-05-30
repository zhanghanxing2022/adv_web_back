package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.User;
import cn.edu.fudan.advweb.backend.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {

    private List<UserInfo> infos;

    public UserListResponse(List<User> users) {
        this.infos = new ArrayList<>();
        for (User user : users) {
            this.infos.add(new UserInfo(user));
        }
    }

    public List<UserInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<UserInfo> infos) {
        this.infos = infos;
    }
}
