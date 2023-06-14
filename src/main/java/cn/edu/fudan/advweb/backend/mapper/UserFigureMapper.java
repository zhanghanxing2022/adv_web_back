package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.UserFigure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserFigureMapper {

    @Select("select * from user_figure where userID = #{userID} order by `order`")
    List<UserFigure> findByUserID(Integer userID);

    @Insert("insert into user_figure (userID, figure, skin, `order`, time) values (#{userID}, #{figure}, #{skin}, #{order}, #{time})")
    int AddUserFigure(UserFigure userFigure);

    @Select("select any_value(userID) as userID, any_value(figure) as figure, any_value(skin) as skin, any_value(`order`) as `order`, any_value(time) as time from user_figure where userID = #{userID} group by figure order by count(figure) desc limit 1")
    UserFigure findFavoriteFigureByUserID(Integer userID);

    @Select("select any_value(userID) as userID, any_value(figure) as figure, any_value(skin) as skin, any_value(`order`) as `order`, any_value(time) as time from user_figure where userID = #{userID} group by skin order by count(skin) desc limit 1")
    UserFigure findFavoriteSkinByUserID(Integer userID);

}
