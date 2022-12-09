package shop.ggamf.ggamf.web;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.ggamf.ggamf.config.dummy.DummyEntity;
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
import shop.ggamf.ggamf.dto.GgamfReqDto.AcceptGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.ReportGgamfReqDto;

@Sql("classpath:db/truncate.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class GgamfApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReasonCodeRepository reasonCodeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private GameCodeRepository gameCodeRepository;
    @Autowired
    private EnterRepository enterRepository;

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
        // Follow : 겜프
        Follow follow1 = followRepository.save(newFollow(ssar, cos));
        Follow follow2 = followRepository.save(newFollow(ssar, lala));
        Follow follow3 = followRepository.save(newFollow(dada, ssar));
        Follow follow4 = followRepository.save(newFollow(kaka, ssar));
        Follow friend5 = followRepository.save(newFriend(ssar, vovo));
        Follow friend6 = followRepository.save(newFriend(toto, ssar));
        // ReasonCode : 신고카테고리
        ReasonCode reason1 = reasonCodeRepository.save(newReasonCode("욕설"));
        ReasonCode reason2 = reasonCodeRepository.save(newReasonCode("탈주"));
        ReasonCode reason3 = reasonCodeRepository.save(newReasonCode("기타"));
        // GameCode : 게임카테고리
        GameCode etc = gameCodeRepository.save(newGameCode("etc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
        // Room : 파티방
        Room endroom1 = roomRepository.save(endRoom("roomname1", ssar, LoL));
        Room room2 = roomRepository.save(newRoom("roomname2", ssar, etc));
        Room room3 = roomRepository.save(newRoom("roomname3", cos, LoL));
        Room endroom4 = roomRepository.save(endRoom("roomname4", lala, etc));
        // Enter : 방 참여 정보
        Enter enter1 = enterRepository.save(endEnter(lala, endroom1));
        Enter enter11 = enterRepository.save(endEnter(dada, endroom1));
        Enter enter111 = enterRepository.save(endEnter(kaka, endroom1));
        Enter enter2 = enterRepository.save(newEnter(cos, room2));
        Enter enter3 = enterRepository.save(newEnter(ssar, room3));
        Enter endEnter1 = enterRepository.save(endEnter(ssar, endroom4));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void followGgamf_test() throws Exception {
        // given
        Long userId = 1L;
        Long followingId = 3L;
        FollowGgamfReqDto followGgamfReqDto = new FollowGgamfReqDto();
        followGgamfReqDto.setFollowerId(userId);
        followGgamfReqDto.setFollowingId(followingId);
        String requestBody = om.writeValueAsString(followGgamfReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/ggamf/user/" + userId + "/follow/" + followingId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.accept").value(false));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void acceptGgamf_test() throws Exception {
        // given
        Long userId = 1L;
        Long followId = 4L;
        AcceptGgamfReqDto acceptGgamfReqDto = new AcceptGgamfReqDto();
        acceptGgamfReqDto.setUserId(userId);
        acceptGgamfReqDto.setFollowId(followId);
        String requestBody = om.writeValueAsString(acceptGgamfReqDto);
        System.out.println("테스트 : " + requestBody);
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/ggamf/user/" + userId + "/accept/" + followId)
                        .content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.accept").value(true));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void deleteGgamf_test() throws Exception {
        // given
        Long followId = 5L;
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.delete("/s/api/ggamf/user/" + userId + "/unfollow/" + followId)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followId").value(5L));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void rejectGgamf_test() throws Exception {
        // given
        Long followId = 3L;
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.delete("/s/api/ggamf/user/" + userId + "/reject/" + followId)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followId").value(3L));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void cancelGgamf_test() throws Exception {
        // given
        Long followId = 1L;
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.delete("/s/api/ggamf/user/" + userId + "/cancel/" + followId)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followId").value(1L));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void reportGgamf_test() throws Exception {
        // given
        Long userId = 1L;
        Long badUserId = 2L;
        ReportGgamfReqDto reportGgamfReqDto = new ReportGgamfReqDto();
        reportGgamfReqDto.setUserId(userId);
        reportGgamfReqDto.setBadUserId(badUserId);
        reportGgamfReqDto.setReasonCodeId(1L);
        reportGgamfReqDto.setDetail("욕설이 너무 심하네요");
        String requestBody = om.writeValueAsString(reportGgamfReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/ggamf/user/" + userId + "/report/" + badUserId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.detail").value("욕설이 너무 심하네요"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findGgamfList_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/ggamf/user/" + userId + "/list"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followers.[0].nickName").value("nickvovo"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followings.[0].nickName").value("nicktoto"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void recommendGgamfList_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/ggamf/user/" + userId + "/recommend"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followers.[0].nickName").value("nickvovo"));
        // resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.followings.[0].nickName").value("nicktoto"));
    }
}
