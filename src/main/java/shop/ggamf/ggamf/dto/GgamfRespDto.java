package shop.ggamf.ggamf.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.user.User;

public class GgamfRespDto {

    @Setter
    @Getter
    public static class FollowGgamfRespDto {
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
        private Long followId;
        private Long userId;
        private String userNick;
        private Long friendId;
        private String friendNick;
        private Boolean accept;

        public AcceptGgamfRespDto(Follow follow) {
            this.followId = follow.getId();
            this.userId = follow.getFollowing().getId();
            this.userNick = follow.getFollowing().getNickname();
            this.friendId = follow.getFollower().getId();
            this.friendNick = follow.getFollower().getNickname();
            this.accept = follow.getAccept();
        }
    }

    @Setter
    @Getter
    public static class DeleteGgamfRespDto {
        private Long followId;
        private Long friendId;
        private String friendNick;

        public DeleteGgamfRespDto(Long followId, User friend) {
            this.followId = followId;
            this.friendId = friend.getId();
            this.friendNick = friend.getNickname();
        }
    }

    @Setter
    @Getter
    public static class RejectGgamfRespDto {
        private Long followId;
        private Long friendId;
        private String friendNick;

        public RejectGgamfRespDto(Long followId, User friend) {
            this.followId = followId;
            this.friendId = friend.getId();
            this.friendNick = friend.getNickname();
        }
    }

    @Setter
    @Getter
    public static class CancelGgamfRespDto {
        private Long followId;
        private Long friendId;
        private String friendNick;

        public CancelGgamfRespDto(Long followId, User friend) {
            this.followId = followId;
            this.friendId = friend.getId();
            this.friendNick = friend.getNickname();
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
    public static class SendGgamfRespDto {

        List<FollowingDto> followings;

        public SendGgamfRespDto(List<Follow> followings) {
            this.followings = followings.stream().map((follow) -> new FollowingDto(follow))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class FollowingDto {
            private Long friendId;
            private String photo;
            private String nickName;
            private String intro;

            public FollowingDto(Follow follow) {
                this.friendId = follow.getFollowing().getId();
                this.photo = follow.getFollowing().getPhoto();
                this.nickName = follow.getFollowing().getNickname();
                this.intro = follow.getFollowing().getIntro();
            }
        }
    }

    @Setter
    @Getter
    public static class ReceiveGgamfRespDto {

        List<FollowerDto> followers;

        public ReceiveGgamfRespDto(List<Follow> followers) {
            this.followers = followers.stream().map((follow) -> new FollowerDto(follow))
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