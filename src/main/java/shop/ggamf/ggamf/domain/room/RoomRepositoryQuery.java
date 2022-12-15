package shop.ggamf.ggamf.domain.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryQuery {

    @Autowired
    private EntityManager em;

    public List<RoomListRespDto> findRoomInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("select r.id, count(*)+1 count, r.total_people, r.room_name, u.nickname from enter e ")
                .append("inner join room r on r.id = e.room_id ")
                .append("inner join users u on u.id = r.user_id ")
                .append("group by r.id");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        try {
            List<RoomListRespDto> roomInfoRespDto = result.list(query, RoomListRespDto.class);
            return roomInfoRespDto;
        } catch (NoResultException e) {
            List<RoomListRespDto> roomInfoRespDto = new ArrayList<>();
            return roomInfoRespDto;
        }

    }
}
