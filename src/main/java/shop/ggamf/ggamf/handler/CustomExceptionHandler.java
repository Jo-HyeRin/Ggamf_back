package shop.ggamf.ggamf.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.dto.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> basic(CustomApiException e) {
        return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), e.getHttpStatus()), e.getHttpStatus());
    }
}
