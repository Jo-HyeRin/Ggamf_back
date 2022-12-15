package shop.ggamf.ggamf.domain.recommendBanuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendBanuserRepository extends JpaRepository<RecommendBanuser, Long> {

    @Query("select r from RecommendBanuser r join fetch r.users u join fetch r.banuser b where u.id = :userId and b.id = :banuserId")
    RecommendBanuser findByBothId(@Param("userId") Long userId, @Param("banuserId") Long banuserId);

    @Query("select r from RecommendBanuser r join fetch r.users u where u.id = :userId")
    List<RecommendBanuser> findByUserId(@Param("userId") Long userId);
}
