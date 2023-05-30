package cn.edu.fudan.advweb.backend.response;

import cn.edu.fudan.advweb.backend.entity.Image;
import cn.edu.fudan.advweb.backend.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class UserImagesResponse {

    List<ImageInfo> infos;

    public UserImagesResponse(List<Image> images) {
        this.infos = new ArrayList<>();
        for (Image image : images)
            this.infos.add(new ImageInfo(image));
    }

    public List<ImageInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ImageInfo> infos) {
        this.infos = infos;
    }
}
