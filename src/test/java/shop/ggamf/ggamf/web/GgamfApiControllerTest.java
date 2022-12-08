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
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.follow.FollowRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.GgamfReqDto.AcceptGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;

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
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void followGgamf_test() throws Exception {
        // given
        Long followingId = 3L;
        FollowGgamfReqDto followGgamfReqDto = new FollowGgamfReqDto();
        followGgamfReqDto.setFollowingId(followingId);
        String requestBody = om.writeValueAsString(followGgamfReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/s/api/ggamf/follow/" + followingId)
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
        Long followId = 4L;
        AcceptGgamfReqDto acceptGgamfReqDto = new AcceptGgamfReqDto();
        acceptGgamfReqDto.setFollowId(followId);
        String requestBody = om.writeValueAsString(acceptGgamfReqDto);
        System.out.println("테스트 : " + requestBody);
        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.put("/s/api/ggamf/accept/" + followId)
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

}
