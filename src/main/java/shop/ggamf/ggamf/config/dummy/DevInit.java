package shop.ggamf.ggamf.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.enter.EnterRepository;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.follow.FollowRepository;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.recommendBanUser.RecommendBanUser;
import shop.ggamf.ggamf.domain.recommendBanUser.RecommendBanUserRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@RequiredArgsConstructor
@Configuration
public class DevInit extends DummyEntity {

    @Profile("dev")
    @Bean
    public CommandLineRunner dataSetting(UserRepository userRepository,
            RoomRepository roomRepository,
            GameCodeRepository gameCodeRepository, EnterRepository enterRepository, FollowRepository followRepository,
            ReasonCodeRepository reasonCodeRepository, RecommendBanUserRepository recommendBanUserRepository) {

        return (args) -> {
            // User : 유저
            User ssar = userRepository.save(newUser("ssar"));
            User cos = userRepository.save(newUser("cos"));
            User lala = userRepository.save(newUser("lala"));
            User dada = userRepository.save(newUser("dada"));
            User kaka = userRepository.save(newUser("kaka"));
            User vovo = userRepository.save(newUser("vovo"));
            User toto = userRepository.save(newUser("toto"));
            User ohoh = userRepository.save(newUser("ohoh"));
            User yeye = userRepository.save(newUser("yeye"));
            User gogo = userRepository.save(newUser("gogo"));
            User romio = userRepository.save(newUser("romio"));
            User jeje = userRepository.save(newUser("jeje"));
            User money = userRepository.save(newUser("money"));
            User terry = userRepository.save(newUser("terry"));
            User wow = userRepository.save(newUser("wow"));
            User cash = userRepository.save(newUser("cash"));
            User power = userRepository.save(newUser("power"));
            User house = userRepository.save(newUser("house"));
            User nero = userRepository.save(newUser("nero"));
            User poll = userRepository.save(newUser("poll"));
            User love = userRepository.save(newUser("love"));
            // Follow : 겜프
            Follow f1 = followRepository.save(newFollow(ssar, cos, false));
            Follow f11 = followRepository.save(newFollow(ssar, vovo, false));
            Follow f2 = followRepository.save(newFollow(lala, ssar, false));
            Follow f22 = followRepository.save(newFollow(toto, ssar, false));
            Follow f3 = followRepository.save(newFollow(ssar, dada, true));
            Follow f33 = followRepository.save(newFollow(ssar, ohoh, true));
            Follow f4 = followRepository.save(newFollow(kaka, ssar, true));
            Follow f44 = followRepository.save(newFollow(yeye, ssar, true));
            // RecommendBanUser : 추천겜프목록에서 제외할 유저
            RecommendBanUser banuser1 = recommendBanUserRepository.save(newBanuser(ssar, romio));
            // ReasonCode : 신고카테고리
            ReasonCode reason1 = reasonCodeRepository.save(newReasonCode("욕설"));
            ReasonCode reason2 = reasonCodeRepository.save(newReasonCode("탈주"));
            ReasonCode reason3 = reasonCodeRepository.save(newReasonCode("기타"));
            // GameCode : 게임카테고리
            GameCode etc = gameCodeRepository.save(newGameCode("etc"));
            GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
            GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
            GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
            // Room : 파티방
            Room endroom1 = roomRepository.save(endRoom("roomname1", ssar, LoL));
            Room room2 = roomRepository.save(newRoom("roomname2", ssar, etc));
            Room room3 = roomRepository.save(newRoom("roomname3", cos, LoL));
            Room endroom4 = roomRepository.save(endRoom("roomname4", lala, etc));
            Room room5 = roomRepository.save(newRoom("roomname5", yeye, starcraft));
            Room room6 = roomRepository.save(newRoom("roomname6", ohoh, battleground));
            Room room7 = roomRepository.save(newRoom("roomname7", vovo, LoL));
            Room room8 = roomRepository.save(newRoom("roomname8", jeje, LoL));
            Room room9 = roomRepository.save(newRoom("roomname9", jeje, LoL));
            Room room10 = roomRepository.save(newRoom("roomname10", jeje, LoL));
            Room room11 = roomRepository.save(newRoom("roomname11", money, LoL));
            Room room12 = roomRepository.save(newRoom("roomname12", money, LoL));
            Room room13 = roomRepository.save(newRoom("roomname13", money, LoL));
            Room room14 = roomRepository.save(newRoom("roomname14", terry, starcraft));
            Room room15 = roomRepository.save(newRoom("roomname15", terry, starcraft));
            Room room16 = roomRepository.save(newRoom("roomname16", terry, starcraft));
            Room room17 = roomRepository.save(newRoom("roomname17", wow, LoL));
            Room room18 = roomRepository.save(newRoom("roomname18", wow, LoL));
            Room room19 = roomRepository.save(newRoom("roomname19", wow, LoL));
            Room room20 = roomRepository.save(newRoom("roomname20", cash, LoL));
            Room room21 = roomRepository.save(newRoom("roomname21", cash, LoL));
            Room room22 = roomRepository.save(newRoom("roomname22", cash, LoL));
            Room room23 = roomRepository.save(newRoom("roomname23", power, etc));
            Room room24 = roomRepository.save(newRoom("roomname24", power, etc));
            Room room25 = roomRepository.save(newRoom("roomname25", power, etc));
            Room room26 = roomRepository.save(newRoom("roomname26", house, LoL));
            Room room27 = roomRepository.save(newRoom("roomname27", house, LoL));
            Room room28 = roomRepository.save(newRoom("roomname28", house, LoL));
            Room room29 = roomRepository.save(newRoom("roomname29", nero, LoL));
            Room room30 = roomRepository.save(newRoom("roomname30", nero, LoL));
            Room room31 = roomRepository.save(newRoom("room222231", nero, LoL));
            Room room32 = roomRepository.save(newRoom("room222232", poll, LoL));
            Room room33 = roomRepository.save(newRoom("room222233", poll, LoL));
            Room room34 = roomRepository.save(newRoom("room222234", poll, LoL));
            Room room35 = roomRepository.save(newRoom("room222235", love, LoL));
            Room room36 = roomRepository.save(newRoom("room222236", love, LoL));
            Room room37 = roomRepository.save(newRoom("room222237", love, LoL));
            // Enter : 방 참여 정보
            Enter enter1 = enterRepository.save(endEnter(lala, endroom1));
            Enter enter11 = enterRepository.save(endEnter(dada, endroom1));
            Enter enter111 = enterRepository.save(endEnter(gogo, endroom1));
            Enter enter2 = enterRepository.save(newEnter(cos, room2));
            Enter enter22 = enterRepository.save(newEnter(kaka, room2));
            Enter enter222 = enterRepository.save(newEnter(romio, room2));
            Enter enter3 = enterRepository.save(newEnter(ssar, room3));
            Enter enter33 = enterRepository.save(newEnter(toto, room3));
            Enter enter333 = enterRepository.save(newEnter(love, room3));
            Enter enter3333 = enterRepository.save(newEnter(money, room3));
            Enter enter33333 = enterRepository.save(newEnter(gogo, room3));
            Enter endEnter4 = enterRepository.save(endEnter(ssar, endroom4));
            Enter endEnter44 = enterRepository.save(endEnter(cos, endroom4));
            Enter endEnter444 = enterRepository.save(endEnter(yeye, endroom4));
            Enter endEnter4444 = enterRepository.save(endEnter(romio, endroom4));
            Enter enter5 = enterRepository.save(newEnter(gogo, room5));
            Enter enter55 = enterRepository.save(newEnter(cos, room5));
            Enter enter555 = enterRepository.save(newEnter(dada, room5));
            Enter enter6 = enterRepository.save(newEnter(ssar, room6));
            Enter enter66 = enterRepository.save(newEnter(lala, room6));
            Enter enter666 = enterRepository.save(newEnter(romio, room6));
            Enter enter6666 = enterRepository.save(newEnter(house, room6));
        };
    }
}
