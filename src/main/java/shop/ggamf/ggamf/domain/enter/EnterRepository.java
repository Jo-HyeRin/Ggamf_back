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
    @Query("select e from Enter e join fetch e.room r where e.room.id = :roomId")
    List<Enter> findByRoomId(@Param("roomId") Long roomId);

    // userId로 찾기
    @Query("select e from Enter e join fetch e.user u where e.user.id = :userId and e.stay = true")
    List<Enter> findByUserId(@Param("userId") Long userId);

    // 추천친구목록 - 방장일 때
    @Query("select distinct e from Enter e join fetch e.room r join fetch e.user u where e.room.id = :roomId and e.stayUntilEnd=true and e.user.id <> :userId")
    List<Enter> findByRoomIdEnd(@Param("roomId") Long roomId, @Param("userId") Long userId);

    // 추천친구목록 - 참여 했을 때
    @Query("select e from Enter e join fetch e.user u join fetch e.room r where e.user.id=:userId and e.stay=false order by r.updatedAt desc")
    List<Enter> findTogether(@Param("userId") Long userId);
}