package shop.ggamf.ggamf.config.jwt;

public interface JwtProperties {
    String SECRET = "껨프"; //비밀값
    int EXPIRATION_TIME = 864000000; // 10일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
