package shop.ggamf.ggamf.domain.user;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.ggamf.ggamf.config.enums.UserEnum;

@AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
public class DetailRespDto {
        private BigInteger id;
        private String photo;
        private String nickname;
        private String intro;
        private UserEnum role;
}
