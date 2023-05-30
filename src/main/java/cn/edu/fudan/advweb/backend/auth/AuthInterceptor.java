package cn.edu.fudan.advweb.backend.auth;

import cn.edu.fudan.advweb.backend.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
        {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
        boolean passed = true;
        if (tokenCheck != null) {
            TokenState[] states = tokenCheck.value();
            String token = request.getHeader("token");
            TokenState state = getTokenState(token);
            if (!Arrays.asList(states).contains(state)) {
                passed = false;
                sendMessage("Permission denied", response);
                System.out.println("Block Unauthorized Request");
            } else {
                token = TokenUtil.create(TokenUtil.get(token, TokenUtil.USERNAME));
                response.setHeader("token", token);
            }
        }
        return passed;
    }

    private TokenState getTokenState(String token) {
        String username = TokenUtil.get(token, TokenUtil.USERNAME);
        TokenState state = TokenState.NON_LOGIN;
        if (username != null) {
            state = TokenState.USER_LOGIN;
        }
        return state;
    }

    private void sendMessage(String message, HttpServletResponse response) throws IOException {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(message);
    }
}
