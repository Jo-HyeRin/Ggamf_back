package shop.ggamf.ggamf.dto;

import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.enums.UserStateEnum;
import shop.ggamf.ggamf.domain.user.User;

public class UserRespDto {
    @Setter
    @Getter
    public static class LoginRespDto {
        private Long id;
        private String username;
        private UserEnum role;
        private String createdAt;

        public LoginRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.role = user.getRole();
            this.createdAt = user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

    }

    @Setter
    @Getter
    public static class JoinRespDto {
        private Long id;
        private String username;
        private UserEnum role;

        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.role = user.getRole();
        }

    }

    @Setter
    @Getter
    public static class UpdateRespDto {
        private Long id;
        private String password;
        private String phone;
        private String nickname;
        private String email;

        public UpdateRespDto(User user) {
            this.id = id;
            this.password = user.getPassword();
            this.phone = user.getPhone();
            this.nickname = user.getNickname();
            this.email = user.getEmail();
        }
    }

    @Setter
    @Getter
    public static class UpdateStateRespDto {
        private UserStateEnum state;

        public UpdateStateRespDto(User user) {
            this.state = state;
        }
    }
}
