package shop.ggamf.ggamf.domain.room;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomInfoRespDto {
    private BigInteger id;
    private BigInteger count;
    private BigInteger totalPeople;
    private String roomName;
    private String nickname;
}
