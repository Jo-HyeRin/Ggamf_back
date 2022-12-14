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
import shop.ggamf.ggamf.domain.recommendBanuser.RecommendBanuser;
import shop.ggamf.ggamf.domain.recommendBanuser.RecommendBanuserRepository;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
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
            ReasonCodeRepository reasonCodeRepository, RecommendBanuserRepository recommendBanuserRepository,
            ReportRepository reportRepository) {

        return (args) -> {
            // User : 유저
            User admin = userRepository.save(newAdmin("admin"));
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
            User poll20 = userRepository.save(newUser("poll20"));
            User love = userRepository.save(newUser("love"));
            User judy = userRepository.save(newUser("judy"));
            // Follow : 겜프
            Follow f1 = followRepository.save(newFollow(ssar, cos, false));
            Follow f11 = followRepository.save(newFollow(ssar, vovo, false));
            Follow f2 = followRepository.save(newFollow(lala, ssar, false));
            Follow f22 = followRepository.save(newFollow(toto, ssar, false));
            Follow f3 = followRepository.save(newFollow(ssar, dada, true));
            Follow f33 = followRepository.save(newFollow(ssar, ohoh, true));
            Follow f4 = followRepository.save(newFollow(kaka, ssar, true));
            Follow f44 = followRepository.save(newFollow(yeye, ssar, true));
            Follow f5 = followRepository.save(newFollow(ssar, terry, true));
            Follow f55 = followRepository.save(newFollow(ssar, wow, true));
            Follow f6 = followRepository.save(newFollow(cash, ssar, false));
            Follow f66 = followRepository.save(newFollow(power, ssar, false));
            // RecommendBanUser : 추천겜프목록에서 제외할 유저
            RecommendBanuser banuser1 = recommendBanuserRepository.save(newBanuser(ssar, romio));
            RecommendBanuser banuser2 = recommendBanuserRepository.save(newBanuser(ssar, nero));
            // ReasonCode : 신고카테고리
            ReasonCode reason1 = reasonCodeRepository.save(newReasonCode("욕설"));
            ReasonCode reason2 = reasonCodeRepository.save(newReasonCode("탈주"));
            ReasonCode reason3 = reasonCodeRepository.save(newReasonCode("대리기사"));
            ReasonCode reason4 = reasonCodeRepository.save(newReasonCode("비매너 행위"));
            ReasonCode reason5 = reasonCodeRepository.save(newReasonCode("기타"));
            // GameCode : 게임카테고리
            GameCode etc = gameCodeRepository.save(newGameCode("etc"));
            GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
            GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
            GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
            GameCode overwatch = gameCodeRepository.save(newGameCode("overwatch"));
            GameCode lostark = gameCodeRepository.save(newGameCode("lostark"));
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
            Room room32 = roomRepository.save(newRoom("room222232", poll20, LoL));
            Room room33 = roomRepository.save(newRoom("room222233", poll20, LoL));
            Room room34 = roomRepository.save(newRoom("room222234", poll20, LoL));
            Room room35 = roomRepository.save(newRoom("room222235", love, LoL));
            Room room36 = roomRepository.save(newRoom("room222236", love, LoL));
            Room room37 = roomRepository.save(newRoom("room222237", ssar, LoL));
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
            Enter endenter7 = enterRepository.save(endEnter(ssar, room7));
            Enter endenter77 = enterRepository.save(endEnter(power, room7));
            Enter endenter777 = enterRepository.save(endEnter(nero, room7));
            Enter endenter7777 = enterRepository.save(endEnter(house, room7));
            Enter endenter77777 = enterRepository.save(endEnter(judy, room7));
            Enter enter37 = enterRepository.save(newEnter(love, room37));
            Enter enter38 = enterRepository.save(newEnter(house, room37));
            // Report : 신고
            Report report1 = reportRepository.save(newReport(ssar, house, reason1));
            Report report2 = reportRepository.save(newReport(cos, house, reason1));
            Report report3 = reportRepository.save(newReport(lala, house, reason4));
            Report report4 = reportRepository.save(newReport(kaka, house, reason1));
            Report report5 = reportRepository.save(newReport(house, ssar, reason2));
            Report report6 = reportRepository.save(newReport(house, dada, reason3));
            Report report7 = reportRepository.save(newReport(jeje, love, reason4));
            Report report8 = reportRepository.save(newReport(nero, judy, reason5));
        };
    }
}
