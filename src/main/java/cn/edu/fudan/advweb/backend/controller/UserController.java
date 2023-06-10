package cn.edu.fudan.advweb.backend.controller;

import cn.edu.fudan.advweb.backend.auth.TokenCheck;
import cn.edu.fudan.advweb.backend.auth.TokenState;
import cn.edu.fudan.advweb.backend.entity.Image;
import cn.edu.fudan.advweb.backend.entity.User;
import cn.edu.fudan.advweb.backend.mapper.ImageMapper;
import cn.edu.fudan.advweb.backend.mapper.UserMapper;
import cn.edu.fudan.advweb.backend.request.UserAddImageRequest;
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
        User user = mapper.findUserByUsername(username);
        session.close();
        return new ResponseEntity<>(new UserProfileResponse(user), HttpStatus.OK);
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

    @GetMapping(value = "/figures", produces = "application/json")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public Object images(@RequestHeader String token) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        ImageMapper mapper = session.getMapper(ImageMapper.class);
        List<Image> images = mapper.findImagesByUsername(username);
        session.close();
        return new UserImagesResponse(images);
    }

    @PostMapping("/addImage")
    @ResponseBody
    @TokenCheck(TokenState.USER_LOGIN)
    public Object addImage(@RequestHeader String token, @RequestBody UserAddImageRequest req) throws IOException {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        SqlSession session = SqlSessionUtil.getSession();
        ImageMapper mapper = session.getMapper(ImageMapper.class);
        int cnt = mapper.findImagesByUsername(username).size();
        mapper.addImage(new Image(req.getName(), username, cnt + 1));
        session.commit();
        session.close();
        return new UserAddImageResponse("Add Image Successfully!");
    }



}
