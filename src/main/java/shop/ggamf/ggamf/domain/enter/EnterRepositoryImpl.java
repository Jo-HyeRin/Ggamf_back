package shop.ggamf.ggamf.domain.enter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;

import lombok.RequiredArgsConstructor;

interface Dao {

    // 추천친구목록 - 내가 참여한 방에서 함께한 인원
    List<Enter> findTogether(@Param("userId") Long userId, @Param("roomIdList") List<Long> roomIdList);

}

@RequiredArgsConstructor
public class EnterRepositoryImpl implements Dao {

    private final EntityManager em;

    @Override
    public List<Enter> findTogether(Long userId, List<Long> roomIdList) {
        String sql = "";
        if (roomIdList == null || roomIdList.isEmpty()) {
            List<Enter> nullList = new ArrayList<>();
            return nullList;
        } else {
            sql += "select e from Enter e join fetch e.user u join fetch e.room ";
            sql += "where e.user.id != :userId and e.room.id in :roomIdList";
            // 파라미터 바인딩
            TypedQuery<Enter> query = em.createQuery(sql, Enter.class);
            if (roomIdList != null) {
                query = query.setParameter("userId", userId);
                query = query.setParameter("roomIdList", roomIdList);
            }
            return query.getResultList();
        }

    }

}