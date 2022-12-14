package shop.ggamf.ggamf.domain;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import shop.ggamf.ggamf.config.dummy.DummyEntity;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.statistics.GameMatchingResponseDto;
import shop.ggamf.ggamf.domain.statistics.StatisticsRepository;
import shop.ggamf.ggamf.domain.statistics.StatisticsRepositoryQuery;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@Import(StatisticsRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class StatisticsRepositoryQueryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private StatisticsRepositoryQuery statisticsRepositoryQuery;

    @Autowired
    private StatisticsRepository StatisticsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GameCodeRepository gameCodeRepository;

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @Test
    public void dto_select_test() {
        // given

        // when
        GameMatchingResponseDto gameMatchingResponseDto = statisticsRepositoryQuery.findGameMatchingStatistics().get(0);

        // then
        Assertions.assertThat(gameMatchingResponseDto.getGameName().equals("etc"));
    }

    private void dummy_init() {
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

        GameCode etc = gameCodeRepository.save(newGameCode("etc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
        GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
        GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));

        Room endroom1 = roomRepository.save(endRoom("roomname1", ssar, LoL));
        Room room2 = roomRepository.save(newRoom("roomname2", ssar, etc));
        Room room3 = roomRepository.save(newRoom("roomname3", cos, LoL));
        Room endroom4 = roomRepository.save(endRoom("roomname4", lala, etc));
        Room room5 = roomRepository.save(newRoom("roomname5", yeye, starcraft));
        Room room6 = roomRepository.save(newRoom("roomname6", ohoh, battleground));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }

}
