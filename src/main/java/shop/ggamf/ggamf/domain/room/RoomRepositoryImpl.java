package shop.ggamf.ggamf.domain.room;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {
    // 추천 겜프 목록 - 내가 닫은 최근 방
    List<Room> findByUserIdEnd(@Param("userId") Long userId);

    // 전체 파티방 목록 보기 - 검색, 카테고리
    List<Room> findAll(@Param("gameCodeId") Long gameCodeId, @Param("keyword") String keyword);

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

    @Override
    public List<Room> findAll(Long gameCodeId, String keyword) {
        String sql = "";
        sql += "select r from Room r join fetch r.gameCode g ";
        sql += "where r.active = true ";

        if (keyword == null || keyword.isEmpty()) {
            sql += "and g.id = :gameCodeId";
        } else {
            sql += "and g.id = :gameCodeId";
            sql += "and r.roomName LIKE %:keyword% ";
        }

        // 파라미터 바인딩
        TypedQuery<Room> query = em.createQuery(sql, Room.class);
        query = query.setParameter("gameCodeId", gameCodeId);
        query = query.setParameter("keyword", keyword);

        return query.getResultList();
    }

}