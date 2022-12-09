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

    public DetailRespDto findDetailById(Long id) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id, photo, nickname, intro, role from users where id = :id");
        
        Query query = em.createNativeQuery(sb.toString())
                .setParameter("id", id); //setParameter 해주기
        
                log.debug("디버그 : 쿼리값좀 보자 :" + query.getSingleResult());

                JpaResultMapper result = new JpaResultMapper();
                DetailRespDto detailRespDto = result.uniqueResult(query, DetailRespDto.class);
                log.debug("디버그 : result : " + detailRespDto.getNickname());

        return detailRespDto;
    }
}