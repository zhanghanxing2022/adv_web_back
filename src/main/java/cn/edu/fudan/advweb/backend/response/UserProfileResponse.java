package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.User;

public class UserProfileResponse {

    private String username;
    private String email;
    private String phone;

    public UserProfileResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
