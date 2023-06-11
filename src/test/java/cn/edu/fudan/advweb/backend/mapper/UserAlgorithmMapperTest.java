package cn.edu.fudan.advweb.backend.mapper;

import cn.edu.fudan.advweb.backend.entity.UserAlgorithm;
import cn.edu.fudan.advweb.backend.entity.UserAlgorithmInfo;
import cn.edu.fudan.advweb.backend.entity.UserAlgorithmType;
import cn.edu.fudan.advweb.backend.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserAlgorithmMapperTest {

    @Test
    public void testFindByUserID() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        List<UserAlgorithm> algorithms = mapper.findByUserID(8);
        System.out.println(algorithms.size());
        session.close();
    }

    @Test
    public void testAddUserAlgorithm() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        UserAlgorithm userAlgorithm = new UserAlgorithm(8, "QuickSort", UserAlgorithmType.PRACTICE, new Date());
        mapper.addUserAlgorithm(userAlgorithm);
        session.commit();
        session.close();
    }

    @Test
    public void testFavoriteAlgorithm() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        UserAlgorithm userAlgorithm = new UserAlgorithm(8, "QuickSort", UserAlgorithmType.LEARN, new Date());
        mapper.addUserAlgorithm(userAlgorithm);
        userAlgorithm = new UserAlgorithm(8, "HeapSort", UserAlgorithmType.LEARN, new Date());
        mapper.addUserAlgorithm(userAlgorithm);
        userAlgorithm = new UserAlgorithm(8, "MergeSort", UserAlgorithmType.LEARN, new Date());
        mapper.addUserAlgorithm(userAlgorithm);
        session.commit();
        String algorithm = mapper.findFavoriteAlgorithmByUserID(8).getAlgorithm();
        System.out.println("algorithm = " + algorithm);
        session.close();
    }

    @Test
    public void testFindInfoByUserID() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        List<UserAlgorithmInfo> infos = mapper.findInfoByUserID(8);
        for (UserAlgorithmInfo info : infos) {
            System.out.println(String.format("algorithm = %s, learn = %d, practice = %d", info.getAlgorithm(), info.getLearn(), info.getPractice()));
        }
        session.close();
    }

    @Test
    public void testFavoriteLearningAlgorithm() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        UserAlgorithm learn = mapper.findFavoriteLearningAlgorithmByUserID(8);
        if (learn == null) {
            System.out.println("learn is null");
        } else {
            System.out.println("learn = " + learn.getAlgorithm());
        }
        session.close();
    }

    @Test
    public void testFavoritePracticingAlgorithm() throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserAlgorithmMapper mapper = session.getMapper(UserAlgorithmMapper.class);
        UserAlgorithm practice = mapper.findFavoritePracticingAlgorithmByUserID(8);
        if (practice == null) {
            System.out.println("practice is null");
        } else {
            System.out.println("practice = " + practice.getAlgorithm());
        }
        session.close();
    }
}
