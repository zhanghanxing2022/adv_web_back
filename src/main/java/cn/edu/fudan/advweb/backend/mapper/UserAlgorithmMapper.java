package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.UserAlgorithm;
import cn.edu.fudan.advweb.backend.entity.UserAlgorithmInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAlgorithmMapper {

    @Select("select * from user_algorithm where userID = #{userID}")
    List<UserAlgorithm> findByUserID(int userID);

    @Insert("insert into user_algorithm (userID, algorithm, type, time) values (#{userID}, #{algorithm}, #{type}, #{time})")
    int addUserAlgorithm(UserAlgorithm userAlgorithm);

    @Select("select any_value(userID) as userID, any_value(algorithm) as algorithm, any_value(type) as type, any_value(time) as time from user_algorithm where userID = #{userID} group by algorithm order by count(algorithm) desc limit 1")
    UserAlgorithm findFavoriteAlgorithmByUserID(int userID);

    @Select("select userID, algorithm, count(type = 'LEARN' or null) learn, count(type = 'PRACTICE' or null) practice from user_algorithm where userID = 8 group by algorithm")
    List<UserAlgorithmInfo> findInfoByUserID(int userID);

    @Select("select any_value(userID) as userID, any_value(algorithm) as algorithm, any_value(type) as type, any_value(time) as time from user_algorithm where userID = #{userID} and type = 'LEARN' group by algorithm order by count(algorithm) desc limit 1")
    UserAlgorithm findFavoriteLearningAlgorithmByUserID(int userID);

    @Select("select any_value(userID) as userID, any_value(algorithm) as algorithm, any_value(type) as type, any_value(time) as time from user_algorithm where userID = #{userID} and type = 'PRACTICE' group by algorithm order by count(algorithm) desc limit 1")
    UserAlgorithm findFavoritePracticingAlgorithmByUserID(int userID);
}
