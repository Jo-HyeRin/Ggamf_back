package shop.ggamf.ggamf.domain.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long>, Dao {

    // 방장 id로 찾기
    @Query("select r from Room r join fetch r.user u where r.user.id = :userId and r.active=true")
    List<Room> findByUserId(@Param("userId") Long userId);

    // 활성화 된 전체 방 찾기
    @Query("select r from Room r where r.active = true")
    List<Room> findByActive();

    // 활성화 된 방 카테고리별 찾기 - 키워드 없는 경우
    @Query("select r from Room r join fetch r.gameCode g where g.id = :gameCodeId and r.active = true")
    List<Room> findByActiveCode(@Param("gameCodeId") Long gameCodeId);

    // 활성화 된 방 카테고리별 찾기 - 키워드 있는 경우
    @Query("select r from Room r join fetch r.gameCode g where g.id = :gameCodeId and r.roomName LIKE %:keyword% and r.active = true")
    List<Room> searchByTitleLike(@Param("gameCodeId") Long gameCodeId, @Param("keyword") String keyword);

}
