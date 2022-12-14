package shop.ggamf.ggamf.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.recommendBanUser.RecommendBanUser;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.user.User;

public class GgamfRespDto {

    @Setter
    @Getter
    public static class FollowGgamfRespDto {
        private Boolean accept;
        private String nickname;
        private String photo;

        public FollowGgamfRespDto(Follow follow) {
            this.accept = follow.getAccept();
            this.nickname = follow.getFollowing().getNickname();
            this.photo = follow.getFollowing().getPhoto();
        }
    }

    @Setter
    @Getter
    public static class AcceptGgamfRespDto {
        private Long userId;
        private String nickname;
        private Boolean accept;

        public AcceptGgamfRespDto(Follow follow) {
            this.userId = follow.getFollower().getId();
            this.nickname = follow.getFollower().getNickname();
            this.accept = follow.getAccept();
        }
    }

    @Setter
    @Getter
    public static class DeleteGgamfRespDto {
        private Long userId;
        private String nickname;

        public DeleteGgamfRespDto(Long followId, User friend) {
            this.userId = friend.getId();
            this.nickname = friend.getNickname();
        }
    }

    @Setter
    @Getter
    public static class RejectGgamfRespDto {
        private Long userId;
        private String nickname;

        public RejectGgamfRespDto(Long followId, User friend) {
            this.userId = friend.getId();
            this.nickname = friend.getNickname();
        }
    }

    @Setter
    @Getter
    public static class CancelGgamfRespDto {
        private Long userId;
        private String nickname;

        public CancelGgamfRespDto(Long followId, User friend) {
            this.userId = friend.getId();
            this.nickname = friend.getNickname();
        }
    }

    @Setter
    @Getter
    public static class ReportGgamfRespDto {
        private String nickname;
        private String reason;
        private String detail;

        public ReportGgamfRespDto(Report report) {
            this.nickname = report.getBadUser().getNickname();
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
            private Long userId;
            private String photo;
            private String nickname;
            private String intro;

            public FollowerDto(Follow follow) {
                this.userId = follow.getFollowing().getId();
                this.photo = follow.getFollowing().getPhoto();
                this.nickname = follow.getFollowing().getNickname();
                this.intro = follow.getFollowing().getIntro();
            }
        }

        @Setter
        @Getter
        public class FollowingDto {
            private Long userId;
            private String photo;
            private String nickname;
            private String intro;

            public FollowingDto(Follow follow) {
                this.userId = follow.getFollower().getId();
                this.photo = follow.getFollower().getPhoto();
                this.nickname = follow.getFollower().getNickname();
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
            private Long userId;
            private String photo;
            private String nickname;
            private String intro;

            public FollowingDto(Follow follow) {
                this.userId = follow.getFollowing().getId();
                this.photo = follow.getFollowing().getPhoto();
                this.nickname = follow.getFollowing().getNickname();
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
            private Long userId;
            private String photo;
            private String nickname;
            private String intro;

            public FollowerDto(Follow follow) {
                this.userId = follow.getFollower().getId();
                this.photo = follow.getFollower().getPhoto();
                this.nickname = follow.getFollower().getNickname();
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
            private String nickname;
            private String intro;

            public UserDto(User user) {
                this.userId = user.getId();
                this.photo = user.getPhoto();
                this.nickname = user.getNickname();
                this.intro = user.getIntro();
            }
        }
    }

    @Setter
    @Getter
    public static class RecommendBanRespDto {
        private String nickname;

        public RecommendBanRespDto(RecommendBanUser recommendBanUser) {
            this.nickname = recommendBanUser.getBanuser().getNickname();
        }
    }
}