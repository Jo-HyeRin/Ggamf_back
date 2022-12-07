package shop.ggamf.ggamf.domain.starRate;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.ggamf.ggamf.dto.UserRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.DetailRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.StarRateRespDto;

@Repository
public class StarRateRepositoryQuery {
    
    @Autowired
    private EntityManager em;

    public StarRateRespDto caculateStaRateById(Long id) {
        StringBuffer sb = new StringBuffer();
        sb.append("select sr.receiverId, avg(sr.rate) from StarRate sr where sr.receiverId = :id");
        
        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        StarRateRespDto starRateRespDto = result.uniqueResult(query, UserRespDto.StarRateRespDto.class);

        return starRateRespDto;
    }
}

