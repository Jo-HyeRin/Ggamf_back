package shop.ggamf.ggamf.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // // 해당 user 겜프 요청 목록
    // @Query("select f from Follow f join fetch f.follower u where u.id = :userId")
    // List<Follow> findByFollowerId(@Param("userId") Long userId);

    // // 해당 user 겜프 받은 목록
    // @Query("select f from Follow f join fetch f.following i where i.id =
    // :userId")
    // List<Follow> findByFollowingId(@Param("userId") Long userId);

}
