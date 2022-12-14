package shop.ggamf.ggamf.domain.recommendBanUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendBanUserRepository extends JpaRepository<RecommendBanUser, Long> {

    @Query("select r from RecommendBanUser r join fetch r.users u join fetch r.banuser b where u.id = :userId and b.id = :banuserId")
    RecommendBanUser findByBothId(@Param("userId") Long userId, @Param("banuserId") Long banuserId);

}
