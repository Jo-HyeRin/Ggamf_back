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
            User cos = userRepository.save(newUser("cos"));
            User lala = userRepository.save(newUser("lala"));
            User dada = userRepository.save(newUser("dada"));
            User kaka = userRepository.save(newUser("kaka"));
            User nana = userRepository.save(newUser("nana"));
            User vovo = userRepository.save(newUser("vovo"));
            User toto = userRepository.save(newUser("toto"));
            User bu = userRepository.save(newUser("bu"));
            User ki = userRepository.save(newUser("ki"));
            User qqq = userRepository.save(newUser("qqq"));
            // GameCode : 게임코드
            GameCode etc = gameCodeRepository.save(newGameCode("etc"));
            GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
            GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
            GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
            // Room : 파티방
            Room room1 = roomRepository.save(newRoom("초보만요1", cos, etc));
            Room room2 = roomRepository.save(newRoom("초보만요2", lala, starcraft));
            Room room3 = roomRepository.save(newRoom("초보만요3", lala, battleground));
            Room room4 = roomRepository.save(newRoom("초보만요4", cos, LoL));
            Room endRoom1 = roomRepository.save(endRoom("종료된방1", cos, LoL));
            Room endRoom2 = roomRepository.save(endRoom("종료된방2", lala, starcraft));
            Room endRoom3 = roomRepository.save(endRoom("종료된방3", cos, battleground));
            Room endRoom4 = roomRepository.save(endRoom("종료된방4", dada, LoL));
            // Enter : 파티방, 참여유저
            Enter enter1 = enterRepository.save(newEnter(lala, room1));
            Enter enter11 = enterRepository.save(newEnter(dada, room1));
            Enter enter111 = enterRepository.save(newEnter(kaka, room1));
            Enter enter2 = enterRepository.save(newEnter(cos, room2));
            Enter enter3 = enterRepository.save(newEnter(cos, room3));
            Enter endEnter1 = enterRepository.save(endEnter(vovo, endRoom1));
            Enter endEnter2 = enterRepository.save(endEnter(bu, endRoom1));
            Enter endEnter3 = enterRepository.save(endEnter(cos, endRoom2));
            Enter endEnter4 = enterRepository.save(endEnter(ki, endRoom2));
            Enter endEnter5 = enterRepository.save(endEnter(toto, endRoom2));
            Enter endEnter6 = enterRepository.save(endEnter(vovo, endRoom3));
            Enter endEnter7 = enterRepository.save(endEnter(qqq, endRoom3));
            Enter endEnter8 = enterRepository.save(endEnter(cos, endRoom4));
            Enter endEnter9 = enterRepository.save(endEnter(bu, endRoom4));
            Enter endEnter10 = enterRepository.save(endEnter(vovo, endRoom4));
            // Follow : 겜프
            Follow follow1 = followRepository.save(newFollower(cos, lala, false));
            Follow follow2 = followRepository.save(newFollowing(lala, cos, false));
            Follow follow3 = followRepository.save(newFollower(cos, dada, true));
            Follow follow4 = followRepository.save(newFollowing(dada, cos, true));

            // ReasonCode : 신고카테고리
            ReasonCode reason1 = reasonCodeRepository.save(newReasonCode("욕설"));
            ReasonCode reason2 = reasonCodeRepository.save(newReasonCode("탈주"));
            ReasonCode reason3 = reasonCodeRepository.save(newReasonCode("기타"));
        };
    }
}
