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
public class ReportRespDto {
    private BigInteger id;
    private String reason;
    private Timestamp createdAt;
    private String submitUser;
    private String badUser;
    private String userState;
}