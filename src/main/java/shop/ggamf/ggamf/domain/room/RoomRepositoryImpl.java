package shop.ggamf.ggamf.domain.room;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {
    // 추천 겜프 목록 - 내가 닫은 방 목록
    // @Query("select r from Room r join fetch r.user u where r.user.id = :userId
    // and r.active=false order by r.updatedAt DESC")
    List<Room> findByUserIdEnd(@Param("userId") Long userId, @Param("limit") int limit);

    // // 첫 번째 값만 가져오기
    // Room findFirstByOrderByIdDesc();
}

@RequiredArgsConstructor
public class RoomRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Room> findByUserIdEnd(Long userId, int limit) {
        return em.createQuery(
                "select r from Room r join fetch r.user u where r.user.id = :userId and r.active=false order by r.updatedAt DESC",
                Room.class).setMaxResults(limit).getResultList();
        // 파라미터 바인딩
        TypedQuery<Transaction> query = em.createQuery(Room.class);
        query = query.setParameter("userId", userId);
        query = query.setParameter("limit", limit);
    }

}
