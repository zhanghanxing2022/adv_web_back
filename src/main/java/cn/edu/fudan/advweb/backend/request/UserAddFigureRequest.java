package cn.edu.fudan.advweb.backend.request;

public class UserAddFigureRequest {

    private String figure;
    private String skin;

    public UserAddFigureRequest() {
    }

    public UserAddFigureRequest(String figure, String skin) {
        this.figure = figure;
        this.skin = skin;
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
}
