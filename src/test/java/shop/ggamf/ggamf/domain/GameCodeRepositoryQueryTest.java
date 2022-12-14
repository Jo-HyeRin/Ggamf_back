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
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepositoryQuery;
import shop.ggamf.ggamf.domain.gameCode.GameListRespDto;

@Import(GameCodeRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class GameCodeRepositoryQueryTest extends DummyEntity {
    @Autowired
    private EntityManager em;

    @Autowired
    private GameCodeRepositoryQuery gameCodeRepositoryQuery;

    @Autowired
    private GameCodeRepository gameCodeRepository;

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @Test
    public void findGameCodeList_test() throws Exception {
        // given

        // when
        GameListRespDto gameListRespDto = gameCodeRepositoryQuery.findGameCodeList().get(0);

        // then
        Assertions.assertThat(gameListRespDto.getGameName()).isEqualTo("etc");
    }

    private void dummy_init() {
        // 게임 목록
        GameCode etc = gameCodeRepository.save(newGameCode("etc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
        GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
        GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
        GameCode finalFantasy14 = gameCodeRepository.save(newGameCode("파이널판타지14"));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }
}
