package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("insert into user (username, password, email, phone) values (#{username}, #{password}, #{email}, #{phone})")
    int addUser(User user);

    @Update("update user set password = #{password} where username = #{username}")
    int updateUser(User user);

}
