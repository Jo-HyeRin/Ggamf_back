package shop.ggamf.ggamf.domain.user;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.ggamf.ggamf.dto.UserRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.DetailRespDto;

@Repository
public class UserRepositoryQuery {
    
    @Autowired
    private EntityManager em;

    public DetailRespDto findDetailById(Long id) {
        StringBuffer sb = new StringBuffer();
        sb.append("select u.id, u.photo, u.nickname, u.intro from User u where u.id = :id");
        
        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        DetailRespDto detailRespDto = result.uniqueResult(query, UserRespDto.DetailRespDto.class);

        return detailRespDto;
    }
}
