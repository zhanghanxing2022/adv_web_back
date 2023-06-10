package cn.edu.fudan.advweb.backend.entity;

import java.util.Date;

public class UserFigure {

    private Integer userID;
    private String figure;
    private String skin;
    private Integer order;
    private Date time;

    public UserFigure() {
    }

    public UserFigure(Integer userID, String figure, String skin, Integer order, Date time) {
        this.userID = userID;
        this.figure = figure;
        this.skin = skin;
        this.order = order;
        this.time = time;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
