package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.User;

public class UserProfileResponse {

    private Integer userID;
    private String username;
    private String email;
    private String phone;
    private String figure;
    private String skin;
    private String algorithm;
    private String learn;
    private String practice;

    public UserProfileResponse(User user, String figure, String skin, String algorithm, String learn, String practice) {
        this.userID = user.getUserID();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.figure = figure;
        this.skin = skin;
        this.algorithm = algorithm;
        this.learn = learn;
        this.practice = practice;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getLearn() {
        return learn;
    }

    public void setLearn(String learn) {
        this.learn = learn;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }
}
