package shop.ggamf.ggamf.domain.report;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailReportRespDto {
    private BigInteger id;
    private String badUser;
    private Timestamp createdAt;
    private String reason;
    private String detail;
    private String submitUser;
    private BigInteger count;
}
