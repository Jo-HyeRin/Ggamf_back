package shop.ggamf.ggamf.domain.room;

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
public class RoomRepositoryQuery {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityManager em;

    public List<RoomListRespDto> findRoomList() { // 쿼리 확인하기
        StringBuffer sb = new StringBuffer();
        sb.append("select r.id, count(*)+1 count, r.total_people, r.room_name, u.nickname from enter e ")
                .append("right outer join room r on r.id = e.room_id ")
                .append("inner join users u on u.id = r.user_id ")
                .append("group by r.id");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        try {
            List<RoomListRespDto> roomInfoRespDto = result.list(query, RoomListRespDto.class);
            log.debug("디버그 : " + roomInfoRespDto);
            return roomInfoRespDto;
        } catch (NoResultException e) {
            List<RoomListRespDto> roomInfoRespDto = new ArrayList<>();
            log.debug("디버그 비었음 : " + roomInfoRespDto);
            return roomInfoRespDto;
        }

    }
}
