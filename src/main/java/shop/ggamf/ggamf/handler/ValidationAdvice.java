package shop.ggamf.ggamf.handler;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    // @Around("postMapping() || putMapping() || deleteMapping() || getMapping()")
    // public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws
    // Throwable {
    // Object[] args = proceedingJoinPoint.getArgs();
    // for (Object arg : args) {
    // if (arg instanceof BindingResult) {
    // BindingResult bindingResult = (BindingResult) arg;

    // if (bindingResult.hasErrors()) {
    // Map<String, String> errorMap = new HashMap<>();

    // for (FieldError error : bindingResult.getFieldErrors()) {
    // errorMap.put(error.getField(), error.getDefaultMessage());
    // }
    // throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
    // }

    // }
    // }
    // return proceedingJoinPoint.proceed();
    // }

}
