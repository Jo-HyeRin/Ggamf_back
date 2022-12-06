package shop.ggamf.ggamf.dto;

import javax.validation.constraints.NotEmpty;
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
        // @NotEmpty(message = "유저네임은 필수입니다.")
        private String username;

        // @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문,숫자,특수문자 최소4에서
        // 최대20까지입니다.")
        private String password;
        private String name;
        private String phone;
        private String nickname;
        private String email;
        // private UserEnum role;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .nickname(nickname)
                    .email(email)
                    .role(UserEnum.USER)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class UpdateReqDto {
        private String password;
        private String phone;
        private String nickname;
        private String email;
        private String intro;

        public UpdateReqDto(String password, String phone, String nickname, String email, String intro) {
            this.password = password;
            this.phone = phone;
            this.nickname = nickname;
            this.email = email;
            this.intro = intro;
        }
    }

    @Setter
    @Getter
    public static class UpdateStateReqDto {
        private UserStateEnum state;

        public UpdateStateReqDto(UserStateEnum state) {
            this.state = state;
        }
    }
}
