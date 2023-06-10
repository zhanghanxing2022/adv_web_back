package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.UserFigure;

import java.util.List;

public class UserFiguresResponse {

    private List<UserFigure> figures;

    public UserFiguresResponse() {
    }

    public UserFiguresResponse(List<UserFigure> figures) {
        this.figures = figures;
    }

    public List<UserFigure> getFigures() {
        return figures;
    }

    public void setFigures(List<UserFigure> figures) {
        this.figures = figures;
    }
}
