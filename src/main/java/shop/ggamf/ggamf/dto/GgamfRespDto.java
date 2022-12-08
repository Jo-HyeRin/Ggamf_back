package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.report.Report;

public class GgamfRespDto {

    @Setter
    @Getter
    public static class FollowGgamfRespDto {
        private Long followerId;
        private Long followingId;
        private Boolean accept;
        private String followingNick;
        private String followingPhoto;

        public FollowGgamfRespDto(Follow follow) {
            this.followerId = follow.getFollower().getId();
            this.followingId = follow.getFollowing().getId();
            this.accept = follow.getAccept();
            this.followingNick = follow.getFollowing().getNickname();
            this.followingPhoto = follow.getFollowing().getPhoto();
        }
    }

    @Setter
    @Getter
    public static class AcceptGgamfRespDto {
        private Long followId;
        private Long userId;
        private Long ggamfId;
        private Boolean accept;

        public AcceptGgamfRespDto(Follow follow) {
            this.followId = follow.getId();
            this.userId = follow.getFollowing().getId();
            this.ggamfId = follow.getFollower().getId();
            this.accept = follow.getAccept();
        }
    }

    @Setter
    @Getter
    public static class DeleteGgamfRespDto {
        private Long followId;
        // 나중에 친구 목록 보여주기

        public DeleteGgamfRespDto(Long followId) {
            this.followId = followId;
        }
    }

    @Setter
    @Getter
    public static class RejectGgamfRespDto {
        private Long followId;
        // 나중에 내가받은겜프요청 목록 보여주기

        public RejectGgamfRespDto(Long followId) {
            this.followId = followId;
        }
    }

    @Setter
    @Getter
    public static class CancelGgamfRespDto {
        private Long followId;
        // 나중에 내가보낸겜프요청 목록 보여주기

        public CancelGgamfRespDto(Long followId) {
            this.followId = followId;
        }
    }

    @Setter
    @Getter
    public static class ReportGgamfRespDto {
        private Long reportId;
        private String submitUserNick;
        private String badUserNick;
        private String reason;
        private String detail;

        public ReportGgamfRespDto(Report report) {
            this.reportId = report.getId();
            this.submitUserNick = report.getSubmitUser().getNickname();
            this.badUserNick = report.getBadUser().getNickname();
            this.reason = report.getReasonCode().getReason();
            this.detail = report.getDetail();
        }
    }

}