package shop.ggamf.ggamf.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // 겜프 요청 - 내가 상대에게 신청한 내역 있는지
    @Query("select f from Follow f join fetch f.user u join fetch f.following i where u.id=:userId and i.id=:friendId")
    List<Follow> findByBothId(@Param("userId") Long userId, @Param("friendId") Long friendId);

    // // 해당 user 겜프 요청 목록
    // @Query("select f from Follow f join fetch f.follower u where u.id = :userId
    // and f.accept = true")
    // List<Follow> findByFollowerId(@Param("userId") Long userId);

    // // 해당 user 겜프 받은 목록
    // @Query("select f from Follow f join fetch f.following i where i.id =:userId
    // and f.accept = true")
    // List<Follow> findByFollowingId(@Param("userId") Long userId);

}
