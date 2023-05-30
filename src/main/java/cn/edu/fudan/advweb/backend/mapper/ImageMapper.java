package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageMapper {

    @Select("select * from user_images where username = 'new-user3'")
    List<Image> findImagesByUsername(String username);

    @Insert("insert into user_images (username, image, `index`) values (#{username}, #{image}, #{index})")
    int addImage(Image image);
}
