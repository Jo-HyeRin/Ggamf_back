package shop.ggamf.ggamf.domain.enter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnterRepository extends JpaRepository<Enter, Long> {

    @Query("select e from Enter e join fetch e.room r join fetch e.user u where e.room.id = :roomId and e.user.id = :userId")
    Optional<Enter> findByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);

    // roomId로 찾기
    @Query("select e from Enter e join fetch e.room r where e.room.id = :roomId and stay=true")
    List<Enter> findByRoomId(@Param("roomId") Long roomId);

    // userId로 찾기
    @Query("select e from Enter e join fetch e.user u where e.user.id = :userId and e.stay = true")
    List<Enter> findByUserId(@Param("userId") Long userId);

    // 추천 겜프 목록 - 참여했던 방
    @Query("select e from Enter e join fetch e.user u join fetch e.room r where e.user.id = :userId and e.room.active = false and e.room.updatedAt = e.updatedAt")
    List<Enter> findRecommendByUserId(@Param("userId") Long userId);

    // 추천 겜프 목록 - 내가 닫은 가장 최근 방 멤버를 roomId로 찾기
    @Query("select e from Enter e join fetch e.room r where e.room.id = :roomId and e.updatedAt = e.room.updatedAt")
    List<Enter> findByLatestRoomId(@Param("roomId") Long roomId);
}