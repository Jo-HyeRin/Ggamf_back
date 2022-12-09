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
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.PartyReqDto.CreateRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.EndRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.ExitRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.JoinRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.KickUserReqDto;

@Sql("classpath:db/truncate.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class PartyApiControllerTest extends DummyEntity {

    private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameCodeRepository gameCodeRepository;
    @Autowired
    private RoomRepository roomRepository;
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
        // GameCode : 게임코드
        GameCode etc = gameCodeRepository.save(newGameCode("etc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL"));
        GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft"));
        GameCode battleground = gameCodeRepository.save(newGameCode("battleground"));
        // Room : 파티방
        Room room1 = roomRepository.save(newRoom("roomname1", ssar, LoL));
        Room room2 = roomRepository.save(newRoom("roomname2", ssar, starcraft));
        Room room3 = roomRepository.save(newRoom("roomname3", cos, battleground));
        Room room4 = roomRepository.save(newRoom("roomname4", lala, etc));
        // Enter : 방 참여 정보
        Enter enter1 = enterRepository.save(newEnter(lala, room1));
        Enter enter11 = enterRepository.save(newEnter(dada, room1));
        Enter enter111 = enterRepository.save(newEnter(kaka, room1));
        Enter enter2 = enterRepository.save(newEnter(cos, room2));
        Enter enter3 = enterRepository.save(newEnter(ssar, room3));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void createRoom_test() throws Exception {
        // given
        Long gameCodeId = 2L;
        CreateRoomReqDto createRoomReqDto = new CreateRoomReqDto();
        createRoomReqDto.setGameCodeId(gameCodeId);
        createRoomReqDto.setRoomName("초보만오세요");
        createRoomReqDto.setTotalPeople(5L);
        String requestBody = om.writeValueAsString(createRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/party/create/" + gameCodeId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.roomName").value("초보만오세요"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void joinRoom_test() throws Exception {
        // given
        Long roomId = 1L;
        JoinRoomReqDto joinRoomReqDto = new JoinRoomReqDto();
        joinRoomReqDto.setRoomId(roomId);
        String requestBody = om.writeValueAsString(joinRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/party/join/" + roomId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value(1L));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void exitRoom_test() throws Exception {
        // given
        Long roomId = 3L;
        ExitRoomReqDto exitRoomReqDto = new ExitRoomReqDto();
        exitRoomReqDto.setRoomId(roomId);
        String requestBody = om.writeValueAsString(exitRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/exit/" + roomId)
                        .content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.stay").value(false));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void endRoom_test() throws Exception {
        // given
        Long roomId = 1L;
        EndRoomReqDto endRoomReqDto = new EndRoomReqDto();
        endRoomReqDto.setRoomId(roomId);
        String requestBody = om.writeValueAsString(endRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/end/" + roomId)
                        .content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.room.active").value(false));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.enters[0].stay").value(false));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void kickUser_test() throws Exception {
        // given
        Long roomId = 1L;
        KickUserReqDto kickUserReqDto = new KickUserReqDto();
        kickUserReqDto.setRoomId(roomId);
        kickUserReqDto.setKickUserId(3L);
        String requestBody = om.writeValueAsString(kickUserReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/kick/" + roomId)
                        .content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.kickUsername").value("lala"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.kickName").value("유저이름"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.stay").value(false));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findByMyIdRoom_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/myrooms"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname1"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void detailRoom_test() throws Exception {
        // given
        Long roomId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/" + roomId));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.roomName").value("roomname1"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findAllRoom() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/list"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname1"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findJoinRooms_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/joins"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname3"));
    }

}
