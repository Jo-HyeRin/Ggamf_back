package shop.ggamf.ggamf.domain.room;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {
    // 내가 가장 최근에 종료한 방
    List<Room> findByUserIdEnd(@Param("userId") Long userId);
}

@RequiredArgsConstructor
public class RoomRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Room> findByUserIdEnd(Long userId) {
        return em.createQuery(
                "select r from Room r join fetch r.user u where r.user.id = :userId and r.active=false order by r.updatedAt DESC",
                Room.class).setParameter("userId", userId).setMaxResults(1)
                .getResultList();
    }

}