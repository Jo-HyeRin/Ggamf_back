package shop.ggamf.ggamf.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u where u.nickname = :nickname")
    Optional<User> findByNickname(@Param("nickname") String nickname);

    @Query("select u from User u where u.phone = :phone")
    Optional<User> findByPhone(@Param("phone") String phone);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    // 추천친구목록보기 / 겜프목록보기
    @Query(value = "select u from User u where u.id in :userList")
    List<User> findByIdFriend(@Param("userList") List<Long> userList);
}
