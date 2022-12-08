package shop.ggamf.ggamf.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u.id, u.photo, u.nickname, u.intro from User u where u.id = :id")
    Optional<User> findDetailById(@Param("id") Long id);
}
