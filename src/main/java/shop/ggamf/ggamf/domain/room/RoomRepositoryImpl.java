package shop.ggamf.ggamf.domain.room;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {

    // 전체 파티방 목록 보기 - 검색, 카테고리, 페이징
    List<Room> findAllRoom(@Param("gameCodeId") Long gameCodeId, @Param("keyword") String keyword,
            @Param("page") Integer page);
}

@RequiredArgsConstructor
public class RoomRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Room> findAllRoom(Long gameCodeId, String keyword, Integer page) {
        String sql = "";
        if (gameCodeId == null) {
            if (keyword == null || keyword.isEmpty()) {
                sql += "select r from Room r where r.active = true";
            } else {
                sql += "select r from Room r ";
                sql += "where r.active = true ";
                sql += "and r.roomName LIKE CONCAT('%', :keyword, '%')";
            }
        } else {
            if (keyword == null || keyword.isEmpty()) {
                sql += "select r from Room r left join r.gameCode g ";
                sql += "where r.active = true ";
                sql += "and g.id = :gameCodeId";
            } else {
                sql += "select r from Room r left join r.gameCode g ";
                sql += "where r.active = true ";
                sql += "and g.id = :gameCodeId ";
                sql += "and r.roomName LIKE CONCAT('%', :keyword, '%')";
            }
        }

        // 파라미터 바인딩
        TypedQuery<Room> query = em.createQuery(sql, Room.class);
        if (gameCodeId == null) {
            if (keyword == null || keyword.isEmpty()) {

            } else {
                query = query.setParameter("keyword", keyword);
            }
        } else {
            if (keyword == null || keyword.isEmpty()) {
                query = query.setParameter("gameCodeId", gameCodeId);
            } else {
                query = query.setParameter("gameCodeId", gameCodeId);
                query = query.setParameter("keyword", keyword);
            }
        }
        query.setFirstResult(page * 10);
        query.setMaxResults(10);
        return query.getResultList();
    }

}