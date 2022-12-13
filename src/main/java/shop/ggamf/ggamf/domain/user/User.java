package shop.ggamf.ggamf.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.enums.UserStateEnum;
import shop.ggamf.ggamf.domain.AuditingTime;

@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User extends AuditingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(unique = true, nullable = false, length = 20)
    private String nickname;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    private Boolean phoneChecked;

    private String photo;

    private String intro;

    @Enumerated(EnumType.STRING)
    private UserStateEnum state;

    @Enumerated(EnumType.STRING) // enum 쓸때 어노테이션
    @Column(nullable = false)
    private UserEnum role;

    private Boolean agree;

    private String uid; // uid추가

    @Builder
    public User(Long id, String username, String password, String name, String phone, String nickname, String email,
            Boolean phoneChecked, String photo, String intro, UserStateEnum state, UserEnum role, Boolean agree) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.email = email;
        this.phoneChecked = phoneChecked;
        this.photo = photo;
        this.intro = intro;
        this.state = state;
        this.role = role;
        this.agree = agree;
    }

    public void 사진수정(String photo) {
        this.photo = photo;
    }

    public void 자기소개수정(String intro) {
        this.intro = intro;
    }

    public void 닉네임수정(String nickname) {
        this.nickname = nickname;
    }

    public void 비밀번호수정(String password) {
        this.password = password;
    }

    public void 전화번호수정(String phone) {
        this.phone = phone;
    }

    public void 이메일수정(String email) {
        this.email = email;
    }

    public void 회원탈퇴(UserStateEnum state) {
        this.state = state.WITHDRAW;
    }

    public void 역할변경(UserEnum role) {
        this.role = role.ADMIN;
    }

}
