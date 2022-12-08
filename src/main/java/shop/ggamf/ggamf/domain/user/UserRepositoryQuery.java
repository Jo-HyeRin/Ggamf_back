package shop.ggamf.ggamf.domain.user;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class UserRepositoryQuery {
    
    @Autowired
    private EntityManager em;

    private final Logger log = LoggerFactory.getLogger(getClass());

    // public HelloDto findHelloDto() {
    //     StringBuffer sb = new StringBuffer();
    //     sb.append("SELECT 1.31 rating from dual");

    //     // 쿼리 완성
    //     Query query = em.createNativeQuery(sb.toString());

    //     // 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 DB결과를 매핑하기 위해서)
    //     JpaResultMapper result = new JpaResultMapper();
    //     HelloDto helloDto = result.uniqueResult(query, HelloDto.class);
    //     return helloDto;
    // }



    public DetailRespDto findDetailById(Long id) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id, photo, nickname, intro from users where id = :id");
        
        Query query = em.createNativeQuery(sb.toString())
                .setParameter("id", id); //setParameter 해주기
        
                log.debug("디버그 : 쿼리값좀 보자 :" + query.getSingleResult());

                JpaResultMapper result = new JpaResultMapper();
                DetailRespDto detailRespDto = result.uniqueResult(query, DetailRespDto.class);
                log.debug("디버그 : result : " + detailRespDto.getNickname());

        return detailRespDto;
    }
}