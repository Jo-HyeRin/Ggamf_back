package shop.ggamf.ggamf.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.user.User;

public class GgamfReqDto {

    @Setter
    @Getter
    public static class FollowGgamfReqDto {
        private Long userId;
        private Long friendId;

        public Follow toEntity(User users, User friend) {
            return Follow.builder()
                    .follower(users)
                    .following(friend)
                    .accept(false)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class ReportGgamfReqDto {

        @NotEmpty(message = "상세 신고 사유는 필수 입력 값입니다.")
        private String detail;
        private Long userId;

        @NotEmpty(message = "신고할 유저는 필수 입력 값입니다.")
        private Long badUserId;

        @NotEmpty(message = "신고 사유 코드는 필수 입력 값입니다.")
        private Long reasonCodeId;

        public Report toEntity(User submitUser, User badUser, ReasonCode reasonCode) {
            return Report.builder()
                    .detail(detail)
                    .submitUser(submitUser)
                    .badUser(badUser)
                    .reasonCode(reasonCode)
                    .build();
        }
    }

}
