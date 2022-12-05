package shop.ggamf.ggamf.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where username = :username")
    Optional<User> findByUsername(@Param("username") String username);
    
    @Query("update User u set password = :password, phone = :phone, nickname = :nickname, email = :email where id = :id")
    User updateById(@Param("id") Long id);
}
