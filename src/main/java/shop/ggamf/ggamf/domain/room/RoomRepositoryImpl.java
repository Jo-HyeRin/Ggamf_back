package shop.ggamf.ggamf.domain.room;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {
    // 추천 겜프 목록 - 내가 닫은 최근 방
    List<Room> findByUserIdEnd(@Param("userId") Long userId);

}

@RequiredArgsConstructor
public class RoomRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Room> findByUserIdEnd(Long userId) {

        String sql = "";
        sql += "select r from Room r ";
        sql += "left join r.user u ";
        sql += "where r.user.id = :userId ";
        sql += "and r.active=false ";
        sql += "order by r.updatedAt DESC";

        // 파라미터 바인딩
        TypedQuery<Room> query = em.createQuery(sql, Room.class);
        query = query.setParameter("userId", userId);
        query = query.setMaxResults(1);

        return query.getResultList();
    }

}
