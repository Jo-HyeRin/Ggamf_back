package shop.ggamf.ggamf.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.report.Report;

public class GgamfRespDto {

    @Setter
    @Getter
    public static class FollowGgamfRespDto {
        // 내 입장
        private Long userId;
        private Long friendId;
        private String friendNick;
        private String friendPhoto;
        private Boolean accept;

        // 친구 입장 (확인용)
        private Long userId2;
        private Long friendId2;
        private String friendNick2;
        private String friendPhoto2;
        private Boolean accept2;

        public FollowGgamfRespDto(Follow myFollow, Follow yourFollow) {
            this.userId = myFollow.getFollower().getId();
            this.friendId = myFollow.getFollowing().getId();
            this.friendNick = myFollow.getFollowing().getNickname();
            this.friendPhoto = myFollow.getFollowing().getPhoto();
            this.accept = myFollow.getAccept();
            this.userId2 = yourFollow.getFollower().getId();
            this.friendId2 = yourFollow.getFollowing().getId();
            this.friendNick2 = yourFollow.getFollowing().getNickname();
            this.friendPhoto2 = yourFollow.getFollowing().getPhoto();
            this.accept2 = yourFollow.getAccept();
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

    @Setter
    @Getter
    public static class GgamfListRespDto {

        List<FollowerDto> followers; // 내가 요청해서 맺은 친구 목록
        List<FollowingDto> followings; // 내가 수락해서 맺은 친구 목록

        public GgamfListRespDto(List<Follow> followers, List<Follow> followings) {
            this.followers = followers.stream().map((follow) -> new FollowerDto(follow)).collect(Collectors.toList());
            this.followings = followings.stream().map((follow) -> new FollowingDto(follow))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class FollowerDto {
            private Long friendId;
            private String photo;
            private String nickName;
            private String intro;

            public FollowerDto(Follow follow) {
                this.friendId = follow.getFollowing().getId();
                this.photo = follow.getFollowing().getPhoto();
                this.nickName = follow.getFollowing().getNickname();
                this.intro = follow.getFollowing().getIntro();
            }
        }

        @Setter
        @Getter
        public class FollowingDto {
            private Long friendId;
            private String photo;
            private String nickName;
            private String intro;

            public FollowingDto(Follow follow) {
                this.friendId = follow.getFollower().getId();
                this.photo = follow.getFollower().getPhoto();
                this.nickName = follow.getFollower().getNickname();
                this.intro = follow.getFollower().getIntro();
            }
        }
    }

    @Setter
    @Getter
    public static class RecommendGgamfListRespDto {

        List<EnterDto> latests; // 내가 최근에 닫은 방 유저들
        List<EnterDto> enters1; // 내가 참여했던 방1 유저들
        List<EnterDto> enters2; // 내가 참여했던 방2 유저들
        List<EnterDto> enters3; // 내가 참여했던 방3 유저들

        public RecommendGgamfListRespDto(List<Enter> latests) {
            this.latests = latests.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
        }

        public RecommendGgamfListRespDto(List<Enter> latests, List<Enter> enters1) {
            this.latests = latests.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters1 = enters1.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
        }

        public RecommendGgamfListRespDto(List<Enter> latests, List<Enter> enters1, List<Enter> enters2) {
            this.latests = latests.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters1 = enters1.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters2 = enters2.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
        }

        public RecommendGgamfListRespDto(List<Enter> latests, List<Enter> enters1, List<Enter> enters2,
                List<Enter> enters3) {
            this.latests = latests.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters1 = enters1.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters2 = enters2.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
            this.enters3 = enters3.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class EnterDto {
            private Long friendId;
            private String photo;
            private String nickName;
            private String intro;

            public EnterDto(Enter enter) {
                this.friendId = enter.getUser().getId();
                this.photo = enter.getUser().getPhoto();
                this.nickName = enter.getUser().getNickname();
                this.intro = enter.getUser().getIntro();
            }
        }
    }
}