package shop.ggamf.ggamf.domain.statistics;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsRepositoryQuery {

    @Autowired
    private EntityManager em;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public void findGameMatchingStatistics() {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select rank() over (order by count) rank, gc.game_name gameName, count(select * from room where active = 'FALSE' group by gc.game_name) count from room r left outer join game_code gc on r.game_code_id = gc.id group by gc.game_name");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        try {
            // List<ReportRespDto> reportRespDto = result.list(query, ReportRespDto.class);
            // log.debug("디버그 : reportRespDto : " + reportRespDto.get(0).getId());

        } catch (NoResultException e) {

        }
    }
}
