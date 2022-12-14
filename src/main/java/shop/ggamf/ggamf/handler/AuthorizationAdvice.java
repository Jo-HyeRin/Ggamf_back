package shop.ggamf.ggamf.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;

@Component
@Aspect
public class AuthorizationAdvice {

    @Pointcut("@annotation(shop.ggamf.ggamf.config.annotations.AuthorizationCheck)")
    public void authorizationCheck() {
    }

    @Around("authorizationCheck()")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String[] paramNames = ((CodeSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        Object[] paramValues = proceedingJoinPoint.getArgs();
        Long userId = null;
        LoginUser loginUser = null;

        for (int i = 0; i < paramNames.length; i++) {
            if (paramNames[i].equals("userId")) {
                userId = (Long) paramValues[i];
            }
            if (paramNames[i].equals("loginUser")) {
                loginUser = (LoginUser) paramValues[i];
            }
        }

        if (userId == null || loginUser == null) {
            throw new CustomApiException("해당 유저가 없습니다", HttpStatus.UNAUTHORIZED);
        }

        // 권한 확인
        if (userId != loginUser.getUser().getId()) {
            throw new CustomApiException("로그인 유저와 요청 유저가 일치하지 않습니다.", HttpStatus.FORBIDDEN);
        }

        return proceedingJoinPoint.proceed();
    }

}