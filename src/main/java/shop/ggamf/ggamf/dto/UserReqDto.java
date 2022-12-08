package shop.ggamf.ggamf.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.enums.UserStateEnum;
import shop.ggamf.ggamf.domain.user.User;

public class UserReqDto {
    @Getter
    @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }

    @ToString
    @Getter
    @Setter
    public static class JoinReqDto {
        @Size(min = 2, max = 20)
        @NotEmpty(message = "아이디는 필수 입력 값입니다.")
        private String username;

        @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 특수문자를 제외한 4 ~ 20 자리입니다.")
        private String password;

        @NotEmpty(message = "이름은 필수 입력 값입니다.")
        private String name;

        @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
        private String phone;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2 ~ 10자리입니다.")
        @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
        private String nickname;

        @NotEmpty(message = "이메일은 필수 입력 값입니다.")
        private String email;
        private Boolean agree;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .nickname(nickname)
                    .email(email)
                    .agree(true)
                    .state(UserStateEnum.NORMAL)
                    .role(UserEnum.USER)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class UpdateReqDto {
        private Long id; //서비스로직
        private String photo;
        private String intro;
        private String nickname;
        private String password;
        private String phone;
        private String email;
    }

    @Setter
    @Getter
    public static class UpdateStateReqDto {
        private Long id; //서비스로직
        private UserStateEnum state;
    }
}
