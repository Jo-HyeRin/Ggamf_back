package shop.ggamf.ggamf.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import shop.ggamf.ggamf.domain.user.UserRepositoryQuery;
import shop.ggamf.ggamf.dto.UserRespDto.DetailRespDto;

@Import(UserRepositoryQuery.class)
@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryQueryTest {
    
    @Autowired
    private UserRepositoryQuery userRepositoryQuery;

    @Test
    public void dto_select_test(Long id) {
        DetailRespDto detailRespDto = userRepositoryQuery.findDetailById(id);
        System.out.println("테스트 : u.nickname : " + detailRespDto.getNickname());
    }
}
