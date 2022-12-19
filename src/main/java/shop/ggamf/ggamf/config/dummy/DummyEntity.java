package shop.ggamf.ggamf.config.dummy;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import shop.ggamf.ggamf.config.enums.UserEnum;
import shop.ggamf.ggamf.config.enums.UserStateEnum;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.recommendBanuser.RecommendBanuser;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.starRate.StarRate;
import shop.ggamf.ggamf.domain.user.User;

public abstract class DummyEntity {

    protected User newUser(String username, String name, String nickname, String phone, String intro) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        String photo = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHYAcAMBIgACEQEDEQH/xAAaAAEAAwEBAQAAAAAAAAAAAAAAAwQFAgYB/8QALRAAAgICAAQDBgcAAAAAAAAAAAECAwQRITFBUQUTMhJCYXGBsSIjUnKCocH/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAwIBBP/EABsRAQEBAQEAAwAAAAAAAAAAAAABAhExEiFB/9oADAMBAAIRAxEAPwD1wAPe8gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATY2PPIlqPCK5yfQiinKSjHm3pG7TXGmqNceS692Z1rjuZ1FVhUVr0e0+8uJK6q2teXDX7Udgl2qcVL8Cua3X+XL+jLshKubhNakjfKniVKsp8xeqHH6G86/KzcskAFGAAAAAAAAE+Ek8urffZtGDRZ5V0J9E+JvJ74rkT363kABNoPk1uEk+TTPpFl2eVjzl11pfM7BhgAukAAAAAAAAF/BzFBKq56Xuy7fAoA5Z12Xj0Kaa2gYVd9lXCFjiu3Qkefka9aX8UT+DXya85xri5TkopdWZGZkvImtLUI8l/pDOydj3Obk/izk3nPHLroADTIAAAAAAF7AxPM1bavwe6u5y3hJ1Dj4ll+nH8MP1Mv1YFMPUnN/EtAndWqTMcxrrh6YRXyR1pAGeuo50U2euuD+hVu8Og+NMnF9nxReAlsORg21Tpl7NkdP7nBvW1Qug4WR2jHyseWPZp8Yv0y7lc66xc8QgA0yAACbFp8+6MX6ecvkbaWlpLS7FHwqGqp2dZPX0ReJbvapmfQADDoAAAAAEeRUrqpQfPo+zJAdHn2nFtS5rgz4WvEoexktr3l7RVLy9Sv0AADZwFrDr1139ywAQvqs8AAcAAAAAAAAGd4slup9eJngFs+J69f/9k=";
        User user = User.builder()
                .username(username)
                .password(encPassword)
                .name(name)
                .phone(phone)
                .nickname(nickname)
                .email(username + "@nate.com")
                .phoneChecked(true)
                .photo(photo)
                .intro(intro)
                .state(UserStateEnum.NORMAL)
                .role(UserEnum.USER)
                .agree(true)
                .uid("uid" + username)
                .build();
        return user;
    }

    protected User newAdmin(String username, String phone, String email) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        String photo = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHYAcAMBIgACEQEDEQH/xAAaAAEAAwEBAQAAAAAAAAAAAAAAAwQFAgYB/8QALRAAAgICAAQDBgcAAAAAAAAAAAECAwQRITFBUQUTMhJCYXGBsSIjUnKCocH/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAwIBBP/EABsRAQEBAQEAAwAAAAAAAAAAAAABAhExEiFB/9oADAMBAAIRAxEAPwD1wAPe8gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATY2PPIlqPCK5yfQiinKSjHm3pG7TXGmqNceS692Z1rjuZ1FVhUVr0e0+8uJK6q2teXDX7Udgl2qcVL8Cua3X+XL+jLshKubhNakjfKniVKsp8xeqHH6G86/KzcskAFGAAAAAAAAE+Ek8urffZtGDRZ5V0J9E+JvJ74rkT363kABNoPk1uEk+TTPpFl2eVjzl11pfM7BhgAukAAAAAAAAF/BzFBKq56Xuy7fAoA5Z12Xj0Kaa2gYVd9lXCFjiu3Qkefka9aX8UT+DXya85xri5TkopdWZGZkvImtLUI8l/pDOydj3Obk/izk3nPHLroADTIAAAAAAF7AxPM1bavwe6u5y3hJ1Dj4ll+nH8MP1Mv1YFMPUnN/EtAndWqTMcxrrh6YRXyR1pAGeuo50U2euuD+hVu8Og+NMnF9nxReAlsORg21Tpl7NkdP7nBvW1Qug4WR2jHyseWPZp8Yv0y7lc66xc8QgA0yAACbFp8+6MX6ecvkbaWlpLS7FHwqGqp2dZPX0ReJbvapmfQADDoAAAAAEeRUrqpQfPo+zJAdHn2nFtS5rgz4WvEoexktr3l7RVLy9Sv0AADZwFrDr1139ywAQvqs8AAcAAAAAAAAGd4slup9eJngFs+J69f/9k=";
        User user = User.builder()
                .username(username)
                .password(encPassword)
                .name("관리자")
                .phone(phone)
                .nickname(username)
                .email(email)
                .phoneChecked(true)
                .photo(photo)
                .intro("관리자입니다")
                .state(UserStateEnum.NORMAL)
                .role(UserEnum.ADMIN)
                .agree(true)
                .uid("uid" + username)
                .build();
        return user;
    }

    protected GameCode newGameCode(String gamename, String logo) {
        GameCode gameCode = GameCode.builder()
                .logo(logo)
                .gameName(gamename)
                .build();
        return gameCode;
    }

    protected Room newRoom(String roomname, User user, GameCode gameCode, String gameName, Long totalPeople) {
        Room room = Room.builder()
                .user(user)
                .gameCode(gameCode)
                .gameName(gameName)
                .roomName(roomname)
                .totalPeople(totalPeople)
                .active(true)
                .build();
        return room;
    }

    protected Room endRoom(String roomname, User user, GameCode gameCode, String gameName, Long totalPeople) {
        Room room = Room.builder()
                .user(user)
                .gameCode(gameCode)
                .gameName(gameName)
                .roomName(roomname)
                .totalPeople(totalPeople)
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

    protected Follow newFollow(User users, User friend, Boolean accept) {
        Follow follow = Follow.builder()
                .follower(users)
                .following(friend)
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

    protected Report newReport(String detail, User submitUser, User badUser, ReasonCode reasonCode) {
        Report report = Report.builder()
                .detail(detail)
                .submitUser(submitUser)
                .badUser(badUser)
                .reasonCode(reasonCode)
                .build();
        return report;
    }

    protected ReasonCode newReason(String reason) {
        ReasonCode reasonCode = ReasonCode.builder()
                .reason(reason)
                .build();
        return reasonCode;
    }

    protected RecommendBanuser newBanuser(User users, User banuser) {
        RecommendBanuser recommendBanuser = RecommendBanuser.builder()
                .users(users)
                .banuser(banuser)
                .build();
        return recommendBanuser;
    }
}
