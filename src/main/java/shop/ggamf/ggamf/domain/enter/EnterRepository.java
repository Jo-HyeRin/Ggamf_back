package shop.ggamf.ggamf.domain.enter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnterRepository extends JpaRepository<Enter, Long> {

    // roomId + userId 로 찾기
    @Query("select e from Enter e join fetch e.room r join fetch e.user u where e.room.id = :roomId and e.user.id = :userId")
    Optional<Enter> findByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);

    // roomId로 찾기
    @Query("select e from Enter e join fetch e.room r where e.room.id = :roomId and e.stay = true")
    List<Enter> findByRoomId(@Param("roomId") Long roomId);

    // userId로 찾기
    @Query("select e from Enter e join fetch e.user u where e.user.id = :userId and e.stay = true")
    List<Enter> findByUserId(@Param("userId") Long userId);

    // 추천친구목록 - 방장일 때 방 종료까지 함께한 인원
    @Query("select e from Enter e join fetch e.room r where e.room.id = :roomId and e.stayUntilEnd=true")
    List<Enter> findByRoomIdEnd(@Param("roomId") Long roomId);

    // 추천친구목록 - 내가 참여한 방 목록
    @Query("select e from Enter e join fetch e.user u where e.user.id=:userId and e.stay=false")
    List<Enter> findEnterRoom(@Param("userId") Long userId);

    // 추천친구목록 - 내가 참여한 방에서 함께한 인원
    @Query("select e from Enter e join fetch e.user u join fetch e.room where e.user.id != :userId and e.room.id in :roomId")
    List<Enter> findTogether(@Param("userId") Long userId, @Param("roomId") List<Long> roomId);

}