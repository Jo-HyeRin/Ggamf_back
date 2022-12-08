package shop.ggamf.ggamf.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
        private UserStateEnum state;
        private UserEnum role;

        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.role = user.getRole();
            this.state = user.getState();
        }

    }

    // @Setter
    // @Getter
    // public static class UpdateRespDto {
    //     private Long id;
    //     private String password;
    //     private String phone;
    //     private String nickname;
    //     private String email;

    //     public UpdateRespDto(User user) {
    //         this.id = id;
    //         this.password = user.getPassword();
    //         this.phone = user.getPhone();
    //         this.nickname = user.getNickname();
    //         this.email = user.getEmail();
    //     }
    // }

    @Setter
    @Getter
    public static class UpdatePhotoRespDto {
        private Long id;
        private String photo;

        public UpdatePhotoRespDto(User user) {
            this.id = user.getId();
            this.photo = user.getPhoto();
        }
    }

    @Setter
    @Getter
    public static class UpdateIntroRespDto {
        private Long id;
        private String intro;

        public UpdateIntroRespDto(User user) {
            this.id = user.getId();
            this.intro = user.getIntro();
        }
    }

    @Setter
    @Getter
    public static class UpdateNicknameRespDto {
        private Long id;
        private String nickname;

        public UpdateNicknameRespDto(User user) {
            this.id = user.getId();
            this.nickname = user.getNickname();
        }
    }

    @Setter
    @Getter
    public static class UpdatePasswordRespDto {
        private Long id;
        private String photo;
        private String intro;
        private String nickname;
        private String password;

        public UpdatePasswordRespDto(User user) {
            this.id = user.getId();
            this.password = user.getPassword();
        }
    }

    @Setter
    @Getter
    public static class UpdatePhoneRespDto {
        private Long id;
        private String phone;
        private String email;

        public UpdateRespDto(User user) {
            this.id = user.getId();
            this.photo = user.getPhoto();
            this.intro = user.getIntro();
            this.nickname = user.getNickname();
            this.password = user.getPassword();
            this.phone = user.getPhone();
            this.email = user.getEmail();
        }
    }

    @Setter
    @Getter
    public static class UpdateStateRespDto {
        private Long id;
        private UserStateEnum state;

        public UpdateStateRespDto(User user) {
            this.id = user.getId();
            this.state = user.getState();
        }
    }

    @Setter
    @Getter
    public static class DetailRespDto {
        private Long id;
        private String photo;
        private String nickname;
        private List<Integer> statics;
        private float starRate;
    }
}
