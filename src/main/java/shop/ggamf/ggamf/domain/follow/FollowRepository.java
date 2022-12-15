package shop.ggamf.ggamf.domain.follow;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long>, Dao {

    // 내가 상대에게 신청한 내역
    @Query("select f from Follow f join fetch f.follower e join fetch f.following i where e.id=:userId and i.id=:friendId and f.accept=false")
    Optional<Follow> findByBothId(@Param("userId") Long userId, @Param("friendId") Long friendId);

    // 겜프사이 확인하기
    @Query("select f from Follow f join fetch f.follower e join fetch f.following i where e.id=:userId and i.id=:friendId and f.accept=true")
    Optional<Follow> isGgamfFollow(@Param("userId") Long userId, @Param("friendId") Long friendId);

    // 겜프 목록보기 (내가 신청해서 겜프 된 경우)
    @Query("select f from Follow f join fetch f.follower e where e.id = :userId and f.accept=true")
    List<Follow> findByFollowerId(@Param("userId") Long userId);

    // 겜프 목록보기 (상대가 신청해서 겜프 된 경우)
    @Query("select f from Follow f join fetch f.following i where i.id = :userId and f.accept=true")
    List<Follow> findByFollowingId(@Param("userId") Long userId);

    // 내가 보낸 겜프 요청 보기
    @Query("select f from Follow f join fetch f.follower e where e.id = :userId and f.accept=false")
    List<Follow> findByUserIdFollower(@Param("userId") Long userId);

    // 내가 받은 겜프 요청 보기
    @Query("select f from Follow f join fetch f.following i where i.id = :userId and f.accept=false")
    List<Follow> findByUserIdFollowing(@Param("userId") Long userId);

}