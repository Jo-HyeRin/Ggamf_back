package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.ggamf.ggamf.domain.gameCode.GameCode;

public class AdminReqDto {
    // public static class saveGameCategoryReqDto {
    // private String name;
    // }

    @ToString
    @Setter
    @Getter
    public static class SaveGameReqDto {
        private String logo;
        private String gameName;

        public GameCode toEntity() {
            return GameCode.builder()
                    .logo(logo)
                    .gameName(gameName)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class UpdateGameReqDto {
        private Long id; // 서비스 로직
        private String logo;
        private String gameName;
    }
}
