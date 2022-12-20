package shop.ggamf.ggamf.domain.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.ggamf.ggamf.dto.PartyRespDto.PeopleDto;

@Repository
public class RoomRepositoryQuery {

    @Autowired
    private EntityManager em;

    public List<RoomListRespDto> findRoomList() { // 쿼리 확인하기
        StringBuffer sb = new StringBuffer();
        sb.append("select r.id, count(*) count, r.total_people, r.room_name, u.nickname from enter e ")
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

    public PeopleDto enterPeople(Long roomId) {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select r.id, count(e.id) count from enter e ")
                .append("left join room r on r.id = e.room_id ")
                .append("where r.active = true ")
                .append("and r.id = :roomId ")
                .append("and e.stay=true ")
                .append("group by r.id");

        Query query = em.createNativeQuery(sb.toString());
        query.setParameter("roomId", roomId);

        JpaResultMapper result = new JpaResultMapper();
        try {
            PeopleDto peopleDto = result.uniqueResult(query, PeopleDto.class);
            return peopleDto;
        } catch (NoResultException e) {
            PeopleDto peopleDto = new PeopleDto();
            return peopleDto;
        }

    }

}
