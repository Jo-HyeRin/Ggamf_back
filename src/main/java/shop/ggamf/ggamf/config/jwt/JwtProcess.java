package shop.ggamf.ggamf.config.jwt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.user.User;

public class JwtProcess {
    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject(loginUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", loginUser.getUser().getId())
                .withClaim("username", loginUser.getUsername())
                .withClaim("role", loginUser.getUser().getRole().name())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return JwtProperties.TOKEN_PREFIX + jwtToken;
    }

    public static LoginUser verify(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token);
        Date expiration = decodedJWT.getExpiresAt();
        if (expiration.before(new Date())) {
            new CustomApiException("해당토큰은 만료되었습니다.", HttpStatus.BAD_REQUEST);
        }
        Long id = decodedJWT.getClaim("id").asLong();
        String username = decodedJWT.getClaim("username").asString();
        String role = decodedJWT.getClaim("role").asString();
        User user = User.builder().id(id).username(username).role(UserEnum.valueOf(role)).build();
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }
}
