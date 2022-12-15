package shop.ggamf.ggamf.domain.statistics;

import java.util.ArrayList;
import java.util.List;

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

    public List<GameMatchingResponseDto> findGameMatchingStatistics() {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select rank() over (order by count(r.game_code_id)) rank, gc.game_name, count(r.game_code_id) count from room r left outer join game_code gc on gc.id = r.game_code_id  where r.active = FALSE group by r.game_code_id");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        try {
            List<GameMatchingResponseDto> gameMatchingResponseDto = result.list(query, GameMatchingResponseDto.class);
            return gameMatchingResponseDto;

        } catch (NoResultException e) {
            List<GameMatchingResponseDto> gameMatchingResponseDto = new ArrayList<>();
            return gameMatchingResponseDto;
        }
    }
}
