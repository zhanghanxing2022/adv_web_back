package cn.edu.fudan.advweb.backend.entity;

import java.util.Date;

public class UserAlgorithm {

    private Integer userID;
    private String algorithm;
    private UserAlgorithmType type;
    private Date time;

    public UserAlgorithm() {
    }

    public UserAlgorithm(Integer userID, String algorithm, UserAlgorithmType type, Date time) {
        this.userID = userID;
        this.algorithm = algorithm;
        this.type = type;
        this.time = time;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
