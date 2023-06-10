package cn.edu.fudan.advweb.backend.response;

public class UserFavoriteResponse {

    private String figure;
    private String skin;

    public UserFavoriteResponse() {
    }

    public UserFavoriteResponse(String figure, String skin) {
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
