package cn.edu.fudan.advweb.backend.entity;

public class ImageInfo {

    private String image;
    private Integer index;

    public ImageInfo() {
    }

    public ImageInfo(Image image) {
        this.image = image.getImage();
        this.index = image.getIndex();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
