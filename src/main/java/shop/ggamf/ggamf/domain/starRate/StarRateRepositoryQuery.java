package shop.ggamf.ggamf.domain.starRate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.ggamf.ggamf.domain.user.StarRateRespDto;

@Repository
public class StarRateRepositoryQuery {
    
    @Autowired
    private EntityManager em;

    public StarRateRespDto caculateStaRateById(Long receiverId) {
        StringBuffer sb = new StringBuffer();
        sb.append("select receiver_id, avg(rate) rate from star_rate where receiver_id = :receiverId group by receiver_id");
        
        Query query = em.createNativeQuery(sb.toString())
        .setParameter("receiverId", receiverId);

        JpaResultMapper result = new JpaResultMapper();
        try {
            StarRateRespDto starRateRespDto = result.uniqueResult(query, StarRateRespDto.class);
            return starRateRespDto;
        } catch (NoResultException e) {
            return new StarRateRespDto();
        }
    }
}
