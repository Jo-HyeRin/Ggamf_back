package shop.ggamf.ggamf.domain.room;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long>, Dao {

    // 방장 id로 찾기
    @Query("select r from Room r join fetch r.user u where r.user.id = :userId and r.active=true")
    List<Room> findByUserId(@Param("userId") Long userId);

    // 추천 겜프 목록 - 내가 닫은 방 목록
    @Query("select r from Room r join fetch r.user u where r.user.id = :userId and r.active=false")
    List<Room> findByUserIdEnd(@Param("userId") Long userId);

}