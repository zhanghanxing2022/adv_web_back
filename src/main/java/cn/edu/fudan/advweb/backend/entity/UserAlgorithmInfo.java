package cn.edu.fudan.advweb.backend.entity;

public class UserAlgorithmInfo {

    private Integer userID;
    private String algorithm;
    private Integer learn;
    private Integer practice;

    public UserAlgorithmInfo() {
    }

    public UserAlgorithmInfo(Integer userID, String algorithm, Integer learn, Integer practice) {
        this.userID = userID;
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

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getLearn() {
        return learn;
    }

    public void setLearn(Integer learn) {
        this.learn = learn;
    }

    public Integer getPractice() {
        return practice;
    }

    public void setPractice(Integer practice) {
        this.practice = practice;
    }
}
