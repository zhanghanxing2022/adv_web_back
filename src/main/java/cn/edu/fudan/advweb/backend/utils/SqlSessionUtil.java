package cn.edu.fudan.advweb.backend.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {

    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory() throws IOException {
        if (factory == null) {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(stream);
        }
        return factory;
    }

    public static SqlSession getSession() throws IOException {
        return getFactory().openSession();
    }

}
