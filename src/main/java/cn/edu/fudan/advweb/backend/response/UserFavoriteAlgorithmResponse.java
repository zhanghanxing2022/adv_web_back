package cn.edu.fudan.advweb.backend.response;

public class UserFavoriteAlgorithmResponse {

    private String total;
    private String learn;
    private String practice;

    public UserFavoriteAlgorithmResponse() {
    }

    public UserFavoriteAlgorithmResponse(String total, String learn, String priactice) {
        this.total = total;
        this.learn = learn;
        this.practice = priactice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
