package shop.ggamf.ggamf.domain.follow;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {
    // 추천친구 목록 - 친구이거나 친구요청중인 유저 찾기
    List<Follow> findByRecommendFollowing(@Param("userId") Long userId, @Param("enters") List<Long> enters);

    // 추천친구 목록 - 친구이거나 친구요청중인 유저 찾기
    List<Follow> findByRecommendFollower(@Param("userId") Long userId, @Param("enters") List<Long> enters);
}

@RequiredArgsConstructor
public class FollowRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Follow> findByRecommendFollowing(Long userId, List<Long> enters) {

        String sql = "";
        sql += "select f from Follow f ";
        sql += "left join f.follower e ";
        sql += "left join f.following i ";
        sql += "where f.follower.id = :userId ";
        sql += "and f.following.id in :enters";

        // 파라미터 바인딩
        TypedQuery<Follow> query = em.createQuery(sql, Follow.class);
        query = query.setParameter("userId", userId);
        query = query.setParameter("enters", enters);

        return query.getResultList();
    }

    @Override
    public List<Follow> findByRecommendFollower(Long userId, List<Long> enters) {

        String sql = "";
        sql += "select f from Follow f ";
        sql += "left join f.follower e ";
        sql += "left join f.following i ";
        sql += "where f.following.id = :userId ";
        sql += "and f.follower.id in :enters";

        // 파라미터 바인딩
        TypedQuery<Follow> query = em.createQuery(sql, Follow.class);
        query = query.setParameter("userId", userId);
        query = query.setParameter("enters", enters);

        return query.getResultList();
    }

}
