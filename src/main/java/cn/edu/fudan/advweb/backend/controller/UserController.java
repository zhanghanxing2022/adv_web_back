package cn.edu.fudan.advweb.backend.controller;

import cn.edu.fudan.advweb.backend.auth.TokenCheck;
import cn.edu.fudan.advweb.backend.auth.TokenState;
import cn.edu.fudan.advweb.backend.entity.User;
import cn.edu.fudan.advweb.backend.entity.UserAlgorithm;
import cn.edu.fudan.advweb.backend.entity.UserAlgorithmInfo;
import cn.edu.fudan.advweb.backend.entity.UserFigure;
import cn.edu.fudan.advweb.backend.mapper.UserAlgorithmMapper;
import cn.edu.fudan.advweb.backend.mapper.UserFigureMapper;
import cn.edu.fudan.advweb.backend.mapper.UserMapper;
import cn.edu.fudan.advweb.backend.request.*;
import cn.edu.fudan.advweb.backend.response.*;
import cn.edu.fudan.advweb.backend.utils.SqlSessionUtil;
import cn.edu.fudan.advweb.backend.utils.TokenUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest req) throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.findUserByUsername(req.getUsername());
        if (user != null) {
            session.close();
            return new ResponseEntity<>("The username is already used!", HttpStatus.BAD_REQUEST);
        }
        user = mapper.findUserByEmail(req.getEmail());
        if (user != null) {
            session.close();
            return new ResponseEntity<>("The email is already used!", HttpStatus.BAD_REQUEST);
        }
        mapper.addUser(new User(req.getUsername(), req.getPassword(), req.getEmail(), req.getPhone()));
        session.commit();
        session.close();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest req) throws IOException {
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.findUserByEmail(req.getEmail());
        session.close();
        if (user == null) {
            return new ResponseEntity<>("User not exists!", HttpStatus.BAD_REQUEST);
        }
        if (!req.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>("Wrong password!", HttpStatus.BAD_REQUEST);
        }
        String token = TokenUtil.create(user.getUsername());
        return new ResponseEntity<>(new UserLoginResponse(token), HttpStatus.OK);
    }

    @GetMapping(value = "/profile", produces = "application/json")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> profile(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        // 查找基本信息
        User user = mapper.findUserByUsername(username);
        int userID = user.getUserID();
        // 查找figure与skin信息
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        String figure = null;
        UserFigure userFigure = userFigureMapper.findFavoriteFigureByUserID(userID);
        if (userFigure != null) {
            figure = userFigure.getFigure();
        }
        String skin = null;
        UserFigure userSkin = userFigureMapper.findFavoriteSkinByUserID(userID);
        if (userSkin != null) {
            skin = userSkin.getSkin();
        }
        // 查找algorithm相关信息
        UserAlgorithmMapper userAlgorithmMapper = session.getMapper(UserAlgorithmMapper.class);
        // 查询最喜欢的算法
        String algorithm = null;
        UserAlgorithm userAlgorithmTotal = userAlgorithmMapper.findFavoriteAlgorithmByUserID(userID);
        if (userAlgorithmTotal != null) {
            algorithm = userAlgorithmTotal.getAlgorithm();
        }
        // 查询最喜欢学习的算法
        String learn = null;
        UserAlgorithm userAlgorithmLearn = userAlgorithmMapper.findFavoriteLearningAlgorithmByUserID(userID);
        if (userAlgorithmLearn != null) {
            learn = userAlgorithmLearn.getAlgorithm();
        }
        // 查询最喜欢练习的算法
        String practice = null;
        UserAlgorithm userAlgorithmPractice = userAlgorithmMapper.findFavoritePracticingAlgorithmByUserID(userID);
        if (userAlgorithmPractice != null) {
            practice = userAlgorithmPractice.getAlgorithm();
        }
        session.close();
        return new ResponseEntity<>(new UserProfileResponse(user, figure, skin, algorithm, learn, practice), HttpStatus.OK);
    }

    @PostMapping("/chpwd")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public Object chpwd(@RequestHeader String token, @RequestBody UserChpwdRequest req) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.findUserByUsername(username);
        if (!req.getOldPassword().equals(user.getPassword())) {
            session.close();
            return new ErrorResponse("Wrong password!");
        }
        user.setPassword(req.getNewPassword());
        mapper.updateUser(user);
        session.commit();
        session.close();
        return new UserChpwdResponse("Change password Successfully!");
    }

    @GetMapping("/figures")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> figures(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByUsername(username);
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        List<UserFigure> figureList = userFigureMapper.findByUserID(user.getUserID());
        session.close();
        return new ResponseEntity<>(new UserFiguresResponse(figureList), HttpStatus.OK);
    }

    @GetMapping("/favoriteFigureAndSkin")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> favoriteFigureAndSkin(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByUsername(username);
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        UserFavoriteFigureAndSkinResponse favorite = new UserFavoriteFigureAndSkinResponse();
        UserFigure figure = userFigureMapper.findFavoriteFigureByUserID(user.getUserID());
        if (figure != null) {
            favorite.setFigure(figure.getFigure());
        }
        UserFigure skin = userFigureMapper.findFavoriteSkinByUserID(user.getUserID());
        if (skin != null) {
            favorite.setSkin(skin.getSkin());
        }
        session.close();
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @PostMapping("/addFigure")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> addFigure(@RequestHeader String token, @RequestBody UserAddFigureRequest req) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByUsername(username);
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        // 先查order是多少
        int order = userFigureMapper.findByUserID(user.getUserID()).size() + 1;
        ResponseEntity<Object> resp = null;
        try {
            userFigureMapper.AddUserFigure(new UserFigure(user.getUserID(), req.getFigure(), req.getSkin(), order, new Date()));
            session.commit();
            resp = new ResponseEntity<>(new UserAddFigureResponse("Add Figure Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<>(new ErrorResponse("Data Error!"), HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
        return resp;
    }

    @GetMapping("/algorithms")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> algorithms(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int userID = userMapper.findUserByUsername(username).getUserID();
        // 通过userID查所有的algorithm
        UserAlgorithmMapper userAlgorithmMapper = session.getMapper(UserAlgorithmMapper.class);
        List<UserAlgorithm> algorithmList = userAlgorithmMapper.findByUserID(userID);
        session.close();
        return new ResponseEntity<>(new UserAlgorithmsResponse(algorithmList), HttpStatus.OK);
    }

    @GetMapping("/algorithmInfo")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> algorithmInfo(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int userID = userMapper.findUserByUsername(username).getUserID();
        // 通过mapper查每个算法的学习和练习次数
        UserAlgorithmMapper userAlgorithmMapper = session.getMapper(UserAlgorithmMapper.class);
        List<UserAlgorithmInfo> infos = userAlgorithmMapper.findInfoByUserID(userID);
        session.close();
        // 封装返回对象
        return new ResponseEntity<>(new UserAlgorithmInfoResponse(infos), HttpStatus.OK);
    }

    @GetMapping("/favoriteAlgorithm")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> favoriteAlgorithm(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int userID = userMapper.findUserByUsername(username).getUserID();
        UserFavoriteAlgorithmResponse favorite = new UserFavoriteAlgorithmResponse();
        // 查询最喜欢的算法
        UserAlgorithmMapper userAlgorithmMapper = session.getMapper(UserAlgorithmMapper.class);
        UserAlgorithm total = userAlgorithmMapper.findFavoriteAlgorithmByUserID(userID);
        if (total != null) {
            favorite.setTotal(total.getAlgorithm());
        }
        // 查询最喜欢学习的算法
        UserAlgorithm learn = userAlgorithmMapper.findFavoriteLearningAlgorithmByUserID(userID);
        if (learn != null) {
            favorite.setLearn(learn.getAlgorithm());
        }
        // 查询最喜欢练习的算法
        UserAlgorithm practice = userAlgorithmMapper.findFavoritePracticingAlgorithmByUserID(userID);
        if (practice != null) {
            favorite.setPractice(practice.getAlgorithm());
        }
        session.close();
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @PostMapping("/addAlgorithm")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> addAlgorithm(@RequestHeader String token, @RequestBody UserAddAlgorithmRequest req) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int userID = userMapper.findUserByUsername(username).getUserID();
        // 添加一条学习或练习记录
        UserAlgorithmMapper userAlgorithmMapper = session.getMapper(UserAlgorithmMapper.class);
        ResponseEntity<Object> resp = null;
        try {
            userAlgorithmMapper.addUserAlgorithm(new UserAlgorithm(userID, req.getAlgorithm(), req.getType(), new Date()));
            session.commit();
            resp = new ResponseEntity<>(new UserAddAlgorithmResponse("Add Algorithm Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<>(new ErrorResponse("Error Data!"), HttpStatus.BAD_REQUEST);
        } finally {
            session.close();
        }
        return resp;
    }

}
