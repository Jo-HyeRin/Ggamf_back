package shop.ggamf.ggamf.domain.user;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StarRateRespDto {
    private BigInteger receiverId;
    private BigDecimal rate;
}
