package shop.ggamf.ggamf.domain.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r join fetch r.user u where r.user.id = :userId")
    List<Room> findByUserId(@Param("userId") Long userId);

    @Query("select r from Room r where active = true")
    List<Room> findByActive();

    // 추천 겜프 목록 - 내가 닫은 방 목록
    @Query("select r from Room r join fetch r.user u where r.user.id = :userId and r.active=false")
    List<Room> findByUserIdEnd(@Param("userId") Long userId);

    // 첫 번째 값만 가져오기
    Room findFirstByOrderByIdDesc();

}
