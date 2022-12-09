package shop.ggamf.ggamf.domain.report;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryQuery {

    @Autowired
    private EntityManager em;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public List<ReportRespDto> findReportList() {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select r.id, rs.reason, r.created_at, u.name submitUser, u.name badUser, u.state userState from report r inner join reason_code rs on rs.id = r.reason_code_id inner join users u on u.id = r.submit_user_id");

        Query query = em.createNativeQuery(sb.toString());

        JpaResultMapper result = new JpaResultMapper();
        try {
            List<ReportRespDto> reportRespDto = result.list(query, ReportRespDto.class);
            log.debug("디버그 : reportRespDto : " + reportRespDto.get(0).getId());
            return reportRespDto;

        } catch (NoResultException e) {
            List<ReportRespDto> reportRespDto = new ArrayList<>();
            return reportRespDto;
        }
    }

}
