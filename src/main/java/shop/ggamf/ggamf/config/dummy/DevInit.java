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
            ReasonCodeRepository reasonCodeRepository) {

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
            // Follow : 겜프
            Follow f1 = followRepository.save(newFollow(ssar, cos, false));
            Follow f11 = followRepository.save(newFollow(ssar, vovo, false));
            Follow f2 = followRepository.save(newFollow(lala, ssar, false));
            Follow f22 = followRepository.save(newFollow(toto, ssar, false));
            Follow f3 = followRepository.save(newFollow(ssar, dada, true));
            Follow f33 = followRepository.save(newFollow(ssar, ohoh, true));
            Follow f4 = followRepository.save(newFollow(kaka, ssar, true));
            Follow f44 = followRepository.save(newFollow(yeye, ssar, true));
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
            // Enter : 방 참여 정보
            Enter enter1 = enterRepository.save(endEnter(lala, endroom1));
            Enter enter11 = enterRepository.save(endEnter(dada, endroom1));
            Enter enter111 = enterRepository.save(endEnter(gogo, endroom1));
            Enter enter2 = enterRepository.save(newEnter(cos, room2));
            Enter enter22 = enterRepository.save(newEnter(kaka, room2));
            Enter enter222 = enterRepository.save(newEnter(romio, room2));
            Enter enter3 = enterRepository.save(newEnter(ssar, room3));
            Enter enter33 = enterRepository.save(newEnter(toto, room3));
            Enter enter333 = enterRepository.save(newEnter(gogo, room3));
            Enter endEnter4 = enterRepository.save(endEnter(ssar, endroom4));
            Enter endEnter44 = enterRepository.save(endEnter(cos, endroom4));
            Enter endEnter444 = enterRepository.save(endEnter(yeye, endroom4));
            Enter endEnter4444 = enterRepository.save(endEnter(romio, endroom4));
        };
    }
}
