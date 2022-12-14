package shop.ggamf.ggamf.domain.gameCode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameCodeRepositoryQuery {

    @Autowired
    private EntityManager em;

    // private final Logger log = LoggerFactory.getLogger(getClass());

    public List<GameListRespDto> findGameCodeList() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT logo, game_name from game_code");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        List<GameListRespDto> gameListRespDto = result.list(query, GameListRespDto.class);
        return gameListRespDto;
    }

}
