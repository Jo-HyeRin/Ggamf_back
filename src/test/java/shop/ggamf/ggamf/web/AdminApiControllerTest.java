package shop.ggamf.ggamf.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.AdminReqDto.SaveGameReqDto;
import shop.ggamf.ggamf.dto.AdminReqDto.UpdateGameReqDto;

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

    @Autowired
    private GameCodeRepository gameCodeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
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

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findDetailReport_Test() throws Exception {
        // given
        Long reportId = 1L;
        Long badUserId = 3L;

        // when
        ResultActions resultActions = mvc.perform(get("/s/api/admin/" + reportId + "/detailReport/" + badUserId));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.reason").value("잘못2"));

    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findGameMatchingList_Test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/s/api/admin/gameMatchingList"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.[0].gameName").value("etc"));

    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void saveGame_Test() throws Exception {
        // given
        SaveGameReqDto saveGameReqDto = new SaveGameReqDto();
        saveGameReqDto.setLogo("사진입니다.");
        saveGameReqDto.setGameName("리그오브레전드");
        String requestBody = om.writeValueAsString(saveGameReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/admin/saveGame").content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.gameName").value("리그오브레전드"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void updateGame_Test() throws Exception {
        // given
        Long id = 3L;
        UpdateGameReqDto updateGameReqDto = new UpdateGameReqDto();
        updateGameReqDto.setLogo("로고입니다");
        updateGameReqDto.setGameName("파이널판타지14");
        String requestBody = om.writeValueAsString(updateGameReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/admin/" + id + "/updateGame").content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.gameName").value("파이널판타지14"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void deleteGame_Test() throws Exception {
        // given
        Long id = 5L;

        // when
        ResultActions resultActions = mvc.perform(delete("/s/api/admin/" + id + "/deleteGame"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        // then
        resultActions.andExpect(status().isOk());
    }

    private void dummy_init() {
        // 유저
        User ssar = userRepository.save(newAdmin("ssar"));
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

        // 게임 목록
        GameCode etc = gameCodeRepository.save(newGameCode("etc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
        GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
        GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
        GameCode finalFantasy14 = gameCodeRepository.save(newGameCode("파이널판타지14"));

        // 방 목록
        Room endroom1 = roomRepository.save(endRoom("roomname1", ssar, LoL));
        Room room2 = roomRepository.save(newRoom("roomname2", ssar, etc));
        Room room3 = roomRepository.save(newRoom("roomname3", cos, LoL));
        Room endroom4 = roomRepository.save(endRoom("roomname4", lala, etc));
        Room room5 = roomRepository.save(newRoom("roomname5", yeye, starcraft));
        Room room6 = roomRepository.save(newRoom("roomname6", ohoh, battleground));

        // 사유
        ReasonCode reason1 = reasonCodeRepository.save(newReason("잘못1"));
        ReasonCode reason2 = reasonCodeRepository.save(newReason("잘못2"));

        // 리포트
        Report report1 = reportRepository.save(newReport(kaka, dada, reason2));
        Report report2 = reportRepository.save(newReport(kaka, cos, reason1));
        Report report3 = reportRepository.save(newReport(dada, kaka, reason1));
    }
}
