package shop.ggamf.ggamf.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.ggamf.ggamf.config.dummy.DummyEntity;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;

@Sql("classpath:db/truncate.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AdminApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReasonCodeRepository reasonCodeRepository;

    @BeforeEach
    public void setUp() {
        autoincrement_reset();
        dummy_init();
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findReportList_Test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc.perform(get("/s/api/admin/" + userId + "/reportList"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.[0].reason").value("잘못2"));

    }

    private void dummy_init() {
        // 유저
        User ssar = userRepository.save(newAdmin("ssar"));
        User cos = userRepository.save(newUser("cos"));
        User kaka = userRepository.save(newUser("kaka"));
        User dada = userRepository.save(newUser("dada"));

        // 사유
        ReasonCode reason1 = reasonCodeRepository.save(newReason("잘못1"));
        ReasonCode reason2 = reasonCodeRepository.save(newReason("잘못2"));

        // 리포트
        Report report1 = reportRepository.save(newReport(kaka, dada, reason2));
        Report report2 = reportRepository.save(newReport(kaka, cos, reason1));
    }

    private void autoincrement_reset() {
        this.em
                .createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1")
                .executeUpdate();
    }
}
