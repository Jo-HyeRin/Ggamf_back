package shop.ggamf.ggamf.domain.room;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoomListRespDto {
    private BigInteger id;
    private BigInteger count;
    private BigInteger totalPeople;
    private String roomName;
    private String nickname;
}
