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

    public DetailReportRespDto findDetailReport(Long id, Long badUserId) {
        StringBuffer sb = new StringBuffer();
        sb.append(
                "select r.id, r.bad_user_id, u.name badUser, r.created_at, rs.reason, r.detail, u.name submitUser, count(select * from report where bad_user_id = :badUserId) count from report r inner join reason_code rs on rs.id = r.reason_code_id inner join users u on u.id = r.submit_user_id where r.id = :id");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter("id", id)
                .setParameter("badUserId", badUserId);

        JpaResultMapper result = new JpaResultMapper();
        try {
            DetailReportRespDto detailReportRespDto = result.uniqueResult(query, DetailReportRespDto.class);
            return detailReportRespDto;

        } catch (NoResultException e) {
            return new DetailReportRespDto();
        }
    }

}
