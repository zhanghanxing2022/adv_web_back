package cn.edu.fudan.advweb.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TokenUtil {

    private static final String SIGN = "adv_web";
    private static final String USERNAME_STR = "username";
    private static final String TYPE_STR = "type";

    public static final int USERNAME = 0;
    public static final int TYPE = 1;

    public static String create(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 3600);
        return JWT.create()
                .withClaim(USERNAME_STR, username)
                .withClaim(TYPE_STR, "global")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    public static String get(String token, int attr) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SIGN)).build();
        String result = null;
        if (token != null) {
            try {
                DecodedJWT decodedJWT = verifier.verify(token);
                switch (attr) {
                    case USERNAME:
                        result = decodedJWT.getClaim(USERNAME_STR).asString();
                        break;
                    case TYPE:
                        result = decodedJWT.getClaim(TYPE_STR).asString();
                        break;
                    default:
                        result = null;
                        break;
                }
            } catch (TokenExpiredException e) {
                System.out.println("token 已过期");
            } catch (AlgorithmMismatchException e) {
                System.out.println("算法异常。");
            } catch (SignatureVerificationException e) {
                System.out.println("签名错误。");
            }
        }
        return result;
    }

    public static Date exp(String token) {
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();
        return claims.get("exp").asDate();
    }

}