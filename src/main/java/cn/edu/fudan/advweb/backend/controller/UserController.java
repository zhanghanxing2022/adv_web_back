package cn.edu.fudan.advweb.backend.controller;

import cn.edu.fudan.advweb.backend.auth.TokenCheck;
import cn.edu.fudan.advweb.backend.auth.TokenState;
import cn.edu.fudan.advweb.backend.entity.User;
import cn.edu.fudan.advweb.backend.entity.UserFigure;
import cn.edu.fudan.advweb.backend.mapper.UserFigureMapper;
import cn.edu.fudan.advweb.backend.mapper.UserMapper;
import cn.edu.fudan.advweb.backend.request.UserAddFigureRequest;
import cn.edu.fudan.advweb.backend.request.UserChpwdRequest;
import cn.edu.fudan.advweb.backend.request.UserLoginRequest;
import cn.edu.fudan.advweb.backend.request.UserRegisterRequest;
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
        // 查找figure与skin信息
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        String figure = userFigureMapper.findFavoriteFigureByUserID(user.getUserID()).getFigure();
        String skin = userFigureMapper.findFavoriteSkinByUserID(user.getUserID()).getSkin();
        session.close();
        return new ResponseEntity<>(new UserProfileResponse(user, figure, skin), HttpStatus.OK);
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

    @GetMapping("/favorite")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public ResponseEntity<Object> favoriteFigureAndSkin(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByUsername(username);
        UserFigureMapper userFigureMapper = session.getMapper(UserFigureMapper.class);
        String figure = userFigureMapper.findFavoriteFigureByUserID(user.getUserID()).getFigure();
        String skin = userFigureMapper.findFavoriteSkinByUserID(user.getUserID()).getSkin();
        session.close();
        return new ResponseEntity<>(new UserFavoriteResponse(figure, skin), HttpStatus.OK);
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
        int result = userFigureMapper.AddUserFigure(new UserFigure(user.getUserID(), req.getFigure(), req.getSkin(), order, new Date()));
        session.commit();
        session.close();
        if (result != 1) {
            return new ResponseEntity<>(new ErrorResponse("Data Error!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new UserAddFigureResponse("Add Figure Successfully!"), HttpStatus.OK);
    }

}
