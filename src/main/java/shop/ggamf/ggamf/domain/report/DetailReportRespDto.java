package shop.ggamf.ggamf.domain.report;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailReportRespDto {
    private Long id;
    private Long badUserId;
    private String badUser;
    private Timestamp createdAt;
    private String reason;
    private String detail;
    private String submitUser;
    private Integer count;
}
