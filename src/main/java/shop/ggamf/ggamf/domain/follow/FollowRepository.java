package shop.ggamf.ggamf.domain.follow;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // // userId로 FollowList 셀렉하기 (신청중,친구 모두)
    // @Query("select f from Follow f join fetch f.users u where u.id =:userId")
    // List<Follow> findByUserId(@Param("userId") Long userId);

    // 내가 상대에게 신청한 내역
    @Query("select f from Follow f join fetch f.users u join fetch f.following i where u.id=:userId and i.id=:friendId")
    Optional<Follow> findByBothId(@Param("userId") Long userId, @Param("friendId") Long friendId);

    // 겜프 목록보기
    @Query("select f from Follow f join fetch f.users u join fetch f.follower e where u.id = :userId and e.id = :userId and f.accept=true")
    List<Follow> findByFollowerId(@Param("userId") Long userId);

    // 겜프 목록보기
    @Query("select f from Follow f join fetch f.users u join fetch f.following i where u.id = :userId and i.id = :userId and f.accept=true")
    List<Follow> findByFollowingId(@Param("userId") Long userId);

    // // 겜프 목록 보기
    // @Query("select f from Follow f join fetch f.users u where u.id = :userId and
    // f.accept=true")
    // List<Follow> findByUsersId(@Param("userId") Long userId);
}