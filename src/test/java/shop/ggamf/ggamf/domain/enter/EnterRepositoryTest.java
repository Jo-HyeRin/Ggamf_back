package shop.ggamf.ggamf.domain.enter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import shop.ggamf.ggamf.config.dummy.DummyEntity;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.follow.FollowRepository;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@ActiveProfiles("test")
@DataJpaTest
public class EnterRepositoryTest extends DummyEntity {

    @Autowired
    private EntityManager em;
    @Autowired
    private EnterRepository enterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GameCodeRepository gameCodeRepository;
    @Autowired
    private FollowRepository followRepository;

    @BeforeEach
    public void setUp() {
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
        // Follow : 겜프
        Follow f1 = followRepository.save(newFollow(ssar, cos, false));
        Follow f11 = followRepository.save(newFollow(ssar, vovo, false));
        Follow f2 = followRepository.save(newFollow(lala, ssar, false));
        Follow f22 = followRepository.save(newFollow(toto, ssar, false));
        Follow f3 = followRepository.save(newFollow(ssar, dada, true));
        Follow f33 = followRepository.save(newFollow(ssar, ohoh, true));
        Follow f4 = followRepository.save(newFollow(kaka, ssar, true));
        Follow f44 = followRepository.save(newFollow(yeye, ssar, true));
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
        Room endroom8 = roomRepository.save(endRoom("roomname8", jeje, LoL));
        Room room9 = roomRepository.save(newRoom("roomname9", jeje, LoL));
        Room room10 = roomRepository.save(newRoom("roomname10", jeje, LoL));
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
        Enter enter5 = enterRepository.save(newEnter(gogo, room5));
        Enter enter55 = enterRepository.save(newEnter(cos, room5));
        Enter enter555 = enterRepository.save(newEnter(dada, room5));
        Enter enter6 = enterRepository.save(newEnter(ssar, room6));
        Enter enter66 = enterRepository.save(newEnter(lala, room6));
        Enter endenter8 = enterRepository.save(endEnter(ssar, endroom8));
        Enter endenter88 = enterRepository.save(endEnter(kaka, endroom8));
        Enter endenter888 = enterRepository.save(endEnter(money, endroom8));
    }

    @AfterEach
    public void tearDown() {
        // auto-increment 초기화 시키는 쿼리
        em.createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE room ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE enter ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
    }

    @Test
    public void findByRoomIdAndUserId_test() throws Exception {
        // given
        Long roomId = 3L;
        Long userId = 1L;

        // when
        Enter enterPS = enterRepository.findByRoomIdAndUserId(roomId, userId)
                .orElseThrow();

        // then
        Assertions.assertThat(enterPS.getId()).isEqualTo(7L);
    }

    @Test
    public void findByRoomId_test() throws Exception {
        // given
        Long roomId = 2L;

        // when
        List<Enter> enterListPS = enterRepository.findByRoomId(roomId);

        // then
        Assertions.assertThat(enterListPS.get(0).getUser().getNickname()).isEqualTo("nickcos");
    }

    @Test
    public void findByUserId_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        List<Enter> enterListPS = enterRepository.findByUserId(userId);

        // then
        Assertions.assertThat(enterListPS.get(0).getRoom().getRoomName()).isEqualTo("roomname3");
    }

    @Test
    public void findByRoomIdEnd_test() throws Exception {
        // given
        Long roomId = 1L;

        // when
        List<Enter> enterListPS = enterRepository.findByRoomIdEnd(roomId);

        // then
        Assertions.assertThat(enterListPS.get(0).getUser().getNickname()).isEqualTo("nicklala");
    }

    @Test
    public void findEnterRoom_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        List<Enter> enterListPS = enterRepository.findEnterRoom(userId);

        // then
        Assertions.assertThat(enterListPS.get(0).getRoom().getRoomName()).isEqualTo("roomname4");
    }

    @Test
    public void findTogether_test() throws Exception {
        // given
        Long userId = 1L;
        List<Long> roomIdList = new ArrayList();
        roomIdList.add(4L);
        roomIdList.add(8L);

        // when
        List<Enter> enterListPS = enterRepository.findTogether(userId, roomIdList);

        // then
        Assertions.assertThat(enterListPS.size()).isEqualTo(5);
    }

}
