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
        dummy_init();
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void createRoomView_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/create"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].gameName").value("etc"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void createRoom_test() throws Exception {
        // given
        Long userId = 1L;
        Long gameCodeId = 2L;
        CreateRoomReqDto createRoomReqDto = new CreateRoomReqDto();
        createRoomReqDto.setGameCodeId(gameCodeId);
        createRoomReqDto.setRoomName("초보만오세요");
        createRoomReqDto.setTotalPeople(5L);
        String requestBody = om.writeValueAsString(createRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/party/user/" + userId + "/create/" + gameCodeId)
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
        Long userId = 1L;
        Long roomId = 7L;
        JoinRoomReqDto joinRoomReqDto = new JoinRoomReqDto();
        joinRoomReqDto.setRoomId(roomId);
        String requestBody = om.writeValueAsString(joinRoomReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/party/user/" + userId + "/join/" + roomId)
                        .content(requestBody)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.userNick").value("nickssar"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void exitRoom_test() throws Exception {
        // given
        Long userId = 1L;
        Long roomId = 3L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/user/" + userId + "/exit/" + roomId)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.roomId").value(3L));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void endRoom_test() throws Exception {
        // given
        Long userId = 1L;
        Long roomId = 2L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/user/" + userId + "/end/" + roomId)
                        .contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.room.id").value(2L));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.enters[0].userNick").value("nickcos"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void kickUser_test() throws Exception {
        // given
        Long userId = 1L;
        Long roomId = 2L;
        KickUserReqDto kickUserReqDto = new KickUserReqDto();
        kickUserReqDto.setKickUserId(5L);
        String requestBody = om.writeValueAsString(kickUserReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/party/user/" + userId + "/kick/" + roomId)
                        .content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.userNick").value("nickkaka"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findByMyIdRoom_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/myrooms"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname2"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void detailRoom_test() throws Exception {
        // given
        Long userId = 1L;
        Long roomId = 3L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/detail/" + roomId));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.room.roomName").value("roomname3"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findAllRoom_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/list"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname2"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void searchRoom_test() throws Exception { // 검색
        // given
        Long userId = 1L;
        String keyword = "2";

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/list?keyword=" + keyword));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname2"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void roomByGameCode_test() throws Exception { // 검색+카테고리
        // given
        Long userId = 1L;
        String keyword = "2";
        Long gameCodeId = 2L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders
                        .get("/s/api/party/user/" + userId + "/list?keyword=" + keyword + "&gameCodeId=" + gameCodeId));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname12"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void roomListPaging_test() throws Exception { // 검색+카테고리+페이징
        // given
        Long userId = 1L;
        String keyword = "2";
        Long gameCodeId = 2L;
        Integer page = 1;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders
                        .get("/s/api/party/user/" + userId + "/list?keyword=" + keyword + "&gameCodeId=" + gameCodeId
                                + "&page=" + page));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("room222233"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void findJoinRooms_test() throws Exception {
        // given
        Long userId = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/s/api/party/user/" + userId + "/joins"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.rooms.[0].roomName").value("roomname3"));
    }

    private void dummy_init() {
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
        User wow = userRepository.save(newUser("wow"));
        User cash = userRepository.save(newUser("cash"));
        User power = userRepository.save(newUser("power"));
        User house = userRepository.save(newUser("house"));
        User nero = userRepository.save(newUser("nero"));
        User poll = userRepository.save(newUser("poll"));
        User love = userRepository.save(newUser("love"));
        // GameCode : 게임카테고리
        GameCode etc = gameCodeRepository.save(newGameCode("etc", "logoetc"));
        GameCode LoL = gameCodeRepository.save(newGameCode("LoL", "logoLoL"));
        GameCode starcraft = gameCodeRepository.save(newGameCode("starcraft", "logostarcraft"));
        GameCode battleground = gameCodeRepository.save(newGameCode("battleground", "logobattleground"));
        // Room : 파티방
        Room endroom1 = roomRepository.save(endRoom("roomname1", ssar, LoL));
        Room room2 = roomRepository.save(newRoom("roomname2", ssar, etc));
        Room room3 = roomRepository.save(newRoom("roomname3", cos, LoL));
        Room endroom4 = roomRepository.save(endRoom("roomname4", lala, etc));
        Room room5 = roomRepository.save(newRoom("roomname5", yeye, starcraft));
        Room room6 = roomRepository.save(newRoom("roomname6", ohoh, battleground));
        Room room7 = roomRepository.save(newRoom("roomname7", vovo, LoL));
        Room room8 = roomRepository.save(newRoom("roomname8", jeje, LoL));
        Room room9 = roomRepository.save(newRoom("roomname9", jeje, LoL));
        Room room10 = roomRepository.save(newRoom("roomname10", jeje, LoL));
        Room room11 = roomRepository.save(newRoom("roomname11", money, LoL));
        Room room12 = roomRepository.save(newRoom("roomname12", money, LoL));
        Room room13 = roomRepository.save(newRoom("roomname13", money, LoL));
        Room room14 = roomRepository.save(newRoom("roomname14", terry, starcraft));
        Room room15 = roomRepository.save(newRoom("roomname15", terry, starcraft));
        Room room16 = roomRepository.save(newRoom("roomname16", terry, starcraft));
        Room room17 = roomRepository.save(newRoom("roomname17", wow, LoL));
        Room room18 = roomRepository.save(newRoom("roomname18", wow, LoL));
        Room room19 = roomRepository.save(newRoom("roomname19", wow, LoL));
        Room room20 = roomRepository.save(newRoom("roomname20", cash, LoL));
        Room room21 = roomRepository.save(newRoom("roomname21", cash, LoL));
        Room room22 = roomRepository.save(newRoom("roomname22", cash, LoL));
        Room room23 = roomRepository.save(newRoom("roomname23", power, etc));
        Room room24 = roomRepository.save(newRoom("roomname24", power, etc));
        Room room25 = roomRepository.save(newRoom("roomname25", power, etc));
        Room room26 = roomRepository.save(newRoom("roomname26", house, LoL));
        Room room27 = roomRepository.save(newRoom("roomname27", house, LoL));
        Room room28 = roomRepository.save(newRoom("roomname28", house, LoL));
        Room room29 = roomRepository.save(newRoom("roomname29", nero, LoL));
        Room room30 = roomRepository.save(newRoom("roomname30", nero, LoL));
        Room room31 = roomRepository.save(newRoom("room222231", nero, LoL));
        Room room32 = roomRepository.save(newRoom("room222232", poll, LoL));
        Room room33 = roomRepository.save(newRoom("room222233", poll, LoL));
        Room room34 = roomRepository.save(newRoom("room222234", poll, LoL));
        Room room35 = roomRepository.save(newRoom("room222235", love, LoL));
        Room room36 = roomRepository.save(newRoom("room222236", love, LoL));
        Room room37 = roomRepository.save(newRoom("room222237", love, LoL));
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
    }
}
