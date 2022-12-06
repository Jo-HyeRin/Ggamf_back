package shop.ggamf.ggamf.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.enter.EnterRepository;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
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
            GameCodeRepository gameCodeRepository, EnterRepository enterRepository) {

        return (args) -> {
            // User : 유저
            User cos = userRepository.save(newUser("cos"));
            User lala = userRepository.save(newUser("lala"));
            User dada = userRepository.save(newUser("dada"));
            User kaka = userRepository.save(newUser("kaka"));
            // GameCode : 게임코드
            GameCode etc = gameCodeRepository.save(newGameCode("etc"));
            GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
            GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
            GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
            // Room : 파티방
            Room room1 = roomRepository.save(newRoom("초보만요1", cos, etc));
            Room room2 = roomRepository.save(newRoom("초보만요2", lala, starcraft));
            Room room3 = roomRepository.save(newRoom("초보만요3", lala, battleground));
            // Enter : 파티방, 참여유저
            Enter enter1 = enterRepository.save(newEnter(lala, room1));
            Enter enter11 = enterRepository.save(newEnter(dada, room1));
            Enter enter111 = enterRepository.save(newEnter(kaka, room1));
            Enter enter2 = enterRepository.save(newEnter(cos, room2));
            Enter enter3 = enterRepository.save(newEnter(cos, room3));
        };
    }
}
