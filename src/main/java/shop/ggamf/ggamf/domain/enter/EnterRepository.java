package shop.ggamf.ggamf.domain.enter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnterRepository extends JpaRepository<Enter, Long> {

    @Query("select e from Enter e join fetch e.room r join fetch e.user u where e.room.id = :roomId and e.user.id = :userId")
    Optional<Enter> findByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);

}