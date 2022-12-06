package shop.ggamf.ggamf.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStateEnum {
    NORMAL("정상"), STOP7("7일 정지"), STOP30("30일 정지"), STOPALL("영구정지"), WITHDRAW("탈퇴") ;

    private String value;
}