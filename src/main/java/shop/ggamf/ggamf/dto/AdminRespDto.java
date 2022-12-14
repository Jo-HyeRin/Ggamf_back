package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.gameCode.GameCode;

public class AdminRespDto {

    @Setter
    @Getter
    public static class SaveGameRespDto {
        private String logo;
        private String gameName;

        public SaveGameRespDto(GameCode gameCode) {
            this.logo = gameCode.getLogo();
            this.gameName = gameCode.getGameName();
        }
    }

    @Setter
    @Getter
    public static class UpdateGameRespDto {
        private Long id;
        private String logo;
        private String gameName;

        public UpdateGameRespDto(GameCode gameCode) {
            this.id = gameCode.getId();
            this.logo = gameCode.getLogo();
            this.gameName = gameCode.getGameName();
        }
    }

    // @Setter
    // @Getter
    // public static class ReportRespDto {
    // private BigInteger id;
    // private String reason;
    // private Timestamp createdAt;
    // private String submitUser;
    // private String badUser;
    // private String userState;
    // }
    // select r.id, rs.reason, r.created_at, u.name submitUser, u.name badUser,
    // u.state userState from report r inner join reason_code rs on rs.id =
    // r.reason_code_id inner join users u on u.id = r.submit_User_id;

    // @Setter
    // @Getter
    // public static class DetailReportRespDto {
    // private Long id;
    // private String badUser;
    // private Timestamp createdAt;
    // private String reason;
    // private String detail;
    // private String submitUser;
    // private Integer count;
    // }

    // select r.id, u.name badUsername, r.created_At, rs.reason, r.detail, u.name
    // submitUsername, (select count r.bad_user_id) form report r where bad_user_id
    // = :badUserId) from report r inner join users u on r.bad_user_id = u.id inner
    // join reason_code rs on rs.id = r.reason_code_id where r.bad_user_id =
    // :badUserId;
}
