package shop.ggamf.ggamf.web;

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
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.domain.user.UserRepositoryQuery;
import shop.ggamf.ggamf.dto.UserReqDto.JoinReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateReqDto;

@Sql("classpath:db/truncate.sql")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserApiControllerTest extends DummyEntity {

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
    private UserRepositoryQuery userRepositoryQuery;

    @BeforeEach
    public void setUp() {
        dummy_init();
    }

    @Test
    public void join_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("asdf");
        joinReqDto.setPassword("1234");
        joinReqDto.setName("asdf");
        joinReqDto.setPhone("01012345678");
        joinReqDto.setNickname("asdf");
        joinReqDto.setEmail("asdf@nate.com");
        String requestBody = om.writeValueAsString(joinReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(post("/s/api/join").content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.data.username").value("asdf"));
    }

    @WithUserDetails(value = "ssar", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    public void update_test() throws Exception {
        // given
        Long id = 1L;
        UpdateReqDto updateReqDto = new UpdateReqDto();
        updateReqDto.setPhoto("사진");
        updateReqDto.setIntro("자기소개1");
        updateReqDto.setPassword("1234");
        updateReqDto.setPhone("01012345678");
        updateReqDto.setNickname("asdf");
        updateReqDto.setEmail("asdf@nate.com");
        String requestBody = om.writeValueAsString(updateReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc
                .perform(put("/s/api/user/" + id + "/update").content(requestBody).contentType(APPLICATION_JSON_UTF8));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("테스트 : " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.nickname").value("asdf"));
    }

    private void dummy_init() {
        User ssar = userRepository.save(newUser("ssar"));
        User cos = userRepository.save(newUser("cos"));
        User lala = userRepository.save(newUser("lala"));
        User dada = userRepository.save(newUser("dada"));
        User kaka = userRepository.save(newUser("kaka"));
    }

}
