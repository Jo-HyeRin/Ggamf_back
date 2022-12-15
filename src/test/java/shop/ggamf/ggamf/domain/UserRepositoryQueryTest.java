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
import shop.ggamf.ggamf.domain.starRate.StarRate;
import shop.ggamf.ggamf.domain.starRate.StarRateRepository;
import shop.ggamf.ggamf.domain.starRate.StarRateRepositoryQuery;
import shop.ggamf.ggamf.domain.user.DetailRespDto;
import shop.ggamf.ggamf.domain.user.StarRateRespDto;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.domain.user.UserRepositoryQuery;

@Import({ UserRepositoryQuery.class, StarRateRepositoryQuery.class })
@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryQueryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepositoryQuery userRepositoryQuery;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StarRateRepository starRateRepository;

    @Autowired
    private StarRateRepositoryQuery starRateRepositoryQuery;

    // @Test
    // public void dto_select_test22() {
    // HelloDto helloDto = userRepositoryQuery.findHelloDto();
    // System.out.println("테스트 : helloDto.id : " + helloDto.getRating());
    // }

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @Test
    public void starRateCaculate_test() {
        // given
        Long receiverId = 2L;

        // when
        StarRateRespDto starRateRespDto = starRateRepositoryQuery.caculateStaRateById(receiverId);

        // then
        Assertions.assertThat(starRateRespDto.getRate());
    }

    @Test
    public void findDetailById_test() {
        // given
        Long id = 1L;

        // when
        DetailRespDto detailRespDto = userRepositoryQuery.findDetailById(id);

        // then
        Assertions.assertThat(detailRespDto.getNickname()).isEqualTo("nickssar");
    }

    private void dummy_init() {
        User ssar = userRepository.save(newUser("ssar"));
        User cos = userRepository.save(newUser("cos"));
        User lala = userRepository.save(newUser("lala"));
        User dada = userRepository.save(newUser("dada"));
        User kaka = userRepository.save(newUser("kaka"));

        StarRate starRate1 = starRateRepository.save(newStarRate(dada, kaka));
        StarRate starRate2 = starRateRepository.save(newStarRate(ssar, cos));
        StarRate starRate3 = starRateRepository.save(newStarRate(ssar, lala));
        StarRate starRate4 = starRateRepository.save(newStarRate(lala, ssar));
        StarRate starRate5 = starRateRepository.save(newStarRate(lala, cos));
        StarRate starRate6 = starRateRepository.save(newStarRate(kaka, cos));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }
}
