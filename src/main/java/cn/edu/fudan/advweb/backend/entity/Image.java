package cn.edu.fudan.advweb.backend.entity;

public class Image {

    private String image;
    private String username;
    private Integer index;

    public Image() {
    }

    public Image(String image, String username, Integer index) {
        this.image = image;
        this.username = username;
        this.index = index;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
