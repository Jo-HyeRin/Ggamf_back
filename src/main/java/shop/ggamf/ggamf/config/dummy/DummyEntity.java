package shop.ggamf.ggamf.config.dummy;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.enums.UserStateEnum;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.starRate.StarRate;
import shop.ggamf.ggamf.domain.user.User;

public abstract class DummyEntity {

    protected User newUser(String username) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        User user = User.builder()
                .username(username)
                .password(encPassword)
                .name("유저이름")
                .phone("010-1234-5678" + username)
                .nickname("nick" + username)
                .email(username + "@nate.com")
                .phoneChecked(true)
                .photo("내사진입니다")
                .intro("안녕하세여 유저입니다")
                .state(UserStateEnum.NORMAL)
                .role(UserEnum.USER)
                .agree(true)
                .build();
        return user;
    }

    protected GameCode newGameCode(String gamename) {
        GameCode gameCode = GameCode.builder()
                .logo(gamename + "사진")
                .gameName(gamename)
                .build();
        return gameCode;
    }

    protected Room newRoom(String roomname, User user, GameCode gameCode) {
        Room room = Room.builder()
                .user(user)
                .gameCode(gameCode)
                .gameName("게임이름")
                .roomName(roomname)
                .totalPeople(3L)
                .active(true)
                .build();
        return room;
    }

    protected Room endRoom(String roomname, User user, GameCode gameCode) {
        Room room = Room.builder()
                .user(user)
                .gameCode(gameCode)
                .gameName("게임이름" + roomname)
                .roomName(roomname)
                .totalPeople(5L)
                .active(false)
                .build();
        return room;
    }

    protected Enter newEnter(User user, Room room) {
        Enter enter = Enter.builder()
                .user(user)
                .room(room)
                .stay(true)
                .stayUntilEnd(false)
                .build();
        return enter;
    }

    protected Enter endEnter(User user, Room room) {
        Enter enter = Enter.builder()
                .user(user)
                .room(room)
                .stay(false)
                .stayUntilEnd(true)
                .build();
        return enter;
    }

    protected Follow newFollowing(User user, User following, Boolean accept) {
        Follow follow = Follow.builder()
                .user(user)
                .follower(user)
                .following(following)
                .accept(accept)
                .build();
        return follow;
    }

    protected Follow newFollower(User user, User follower, Boolean accept) {
        Follow follow = Follow.builder()
                .user(user)
                .follower(follower)
                .following(user)
                .accept(accept)
                .build();
        return follow;
    }

    protected StarRate newStarRate(User giver, User receiver) {
        Random random = new Random();
        StarRate starRate = StarRate.builder()
                .giver(giver)
                .receiver(receiver)
                .rate(random.nextLong())
                .build();
        return starRate;

    }

    protected ReasonCode newReasonCode(String reason) {
        ReasonCode reasonCode = ReasonCode.builder()
                .reason(reason)
                .build();
        return reasonCode;
    }
}
