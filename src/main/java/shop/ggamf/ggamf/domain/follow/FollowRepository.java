package shop.ggamf.ggamf.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long>, Dao {

    // 해당 user 겜프 요청 목록
    @Query("select f from Follow f join fetch f.follower u where u.id = :userId and f.accept = true")
    List<Follow> findByFollowerId(@Param("userId") Long userId);

    // 해당 user 겜프 받은 목록
    @Query("select f from Follow f join fetch f.following i where i.id =:userId and f.accept = true")
    List<Follow> findByFollowingId(@Param("userId") Long userId);

}
