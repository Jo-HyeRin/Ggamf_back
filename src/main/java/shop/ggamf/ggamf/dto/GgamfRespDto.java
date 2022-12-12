package shop.ggamf.ggamf.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.user.User;

public class GgamfRespDto {

    @Setter
    @Getter
    public static class FollowGgamfRespDto {
        // 내가 요청한 정보를 본다.
        private Long followerId;
        private Long followingId;
        private Boolean accept;
        private String followingNick;
        private String followingPhoto;

        public FollowGgamfRespDto(Follow follower, Follow following) {
            this.followerId = follower.getFollower().getId();
            this.followingId = follower.getFollowing().getId();
            this.accept = follower.getAccept();
            this.followingNick = follower.getFollowing().getNickname();
            this.followingPhoto = follower.getFollowing().getPhoto();
        }
    }

    @Setter
    @Getter
    public static class AcceptGgamfRespDto {
        // 수락하는 입장에서
        private Long followId;
        private Long userId;
        private Long ggamfId;
        private Boolean accept;

        public AcceptGgamfRespDto(Follow follower, Follow folloing) {
            this.followId = follower.getId();
            this.userId = follower.getFollowing().getId();
            this.ggamfId = follower.getFollower().getId();
            this.accept = follower.getAccept();
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

        List<UserDto> recommendUserList;

        public RecommendGgamfListRespDto(List<User> recommendUserList) {
            this.recommendUserList = recommendUserList.stream().map((user) -> new UserDto(user))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class UserDto {
            private Long userId;
            private String photo;
            private String nickName;
            private String intro;

            public UserDto(User user) {
                this.userId = user.getId();
                this.photo = user.getPhoto();
                this.nickName = user.getNickname();
                this.intro = user.getIntro();
            }
        }
    }
}