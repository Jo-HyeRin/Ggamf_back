package shop.ggamf.ggamf.domain.statistics;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GameMatchingResponseDto {
    private BigInteger Rank;
    private String gameName;
    private BigInteger count;
}
