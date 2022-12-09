package shop.ggamf.ggamf.domain.starRate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StarRateRepository extends JpaRepository<StarRate, Long> {

    // @Query("select sr.receiverId, avg(sr.rate) from StarRate sr where sr.receiverId = :receiverId")
    // Optional<StarRate> caculateStaRateById (@Param("receiverId") Long receiverId);
}
