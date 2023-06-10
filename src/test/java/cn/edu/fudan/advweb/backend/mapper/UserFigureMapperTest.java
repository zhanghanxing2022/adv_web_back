package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.UserFigure;
import cn.edu.fudan.advweb.backend.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserFigureMapperTest {

    @Test
    public void testAddUserFigure() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserFigureMapper mapper = session.getMapper(UserFigureMapper.class);
        UserFigure userFigure = new UserFigure(8, "rabbit", "rabbit", 5, new Date());
        mapper.AddUserFigure(userFigure);
        session.commit();
    }

    @Test
    public void testListUserFigureByUserID() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserFigureMapper mapper = session.getMapper(UserFigureMapper.class);
        List<UserFigure> userFigures = mapper.findByUserID(8);
        System.out.println(userFigures.size());
    }

    @Test
    public void testFavoriteFigureByUserID() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserFigureMapper mapper = session.getMapper(UserFigureMapper.class);
        UserFigure figure = mapper.findFavoriteFigureByUserID(8);
        System.out.println(figure.getFigure());
    }

}
