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
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.report.DetailReportRespDto;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.report.ReportRepositoryQuery;
import shop.ggamf.ggamf.domain.report.ReportRespDto;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@Import(ReportRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class ReportRepositoryQueryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private ReportRepositoryQuery reportRepositoryQuery;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReasonCodeRepository reasonCodeRepository;

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @Test
    public void dto_select_test4() {
        // given

        // when
        ReportRespDto reportRespDto = reportRepositoryQuery.findReportList().get(0);

        // then
        Assertions.assertThat(reportRespDto.getReason()).isEqualTo("잘못1");
    }

    @Test
    public void findDetailReport_test() {
        // given
        Long id = 1L;
        Long badUserId = 3L;
        // when
        DetailReportRespDto detailReportRespDto = reportRepositoryQuery.findDetailReport(id, badUserId);

        // then
        Assertions.assertThat(detailReportRespDto.getDetail()).isEqualTo("이러저러한사유");
    }

    private void dummy_init() {
        User ssar = userRepository.save(newUser("ssar"));
        User cos = userRepository.save(newUser("cos"));
        User lala = userRepository.save(newUser("lala"));
        User dada = userRepository.save(newUser("dada"));
        User kaka = userRepository.save(newUser("kaka"));

        ReasonCode reason1 = reasonCodeRepository.save(newReason("잘못1"));
        ReasonCode reason2 = reasonCodeRepository.save(newReason("잘못2"));

        Report report1 = reportRepository.save(newReport(dada, kaka, reason1));
        Report report2 = reportRepository.save(newReport(cos, kaka, reason2));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }
}
