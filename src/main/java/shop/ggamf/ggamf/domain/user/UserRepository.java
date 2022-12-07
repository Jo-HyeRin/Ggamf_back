package shop.ggamf.ggamf.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
    
    //update문 사용하지말고 걍 set으로 더티체킹하기..
    //참조 https://github.com/codingspecialist/bank-study-green/blob/master/src/main/java/shop/mtcoding/bank/service/TransactionService.java
}
