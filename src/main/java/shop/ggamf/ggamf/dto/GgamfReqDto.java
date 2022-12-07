package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.user.User;

public class GgamfReqDto {

    @Setter
    @Getter
    public static class FollowGgamfReqDto {
        private Long followerId;
        private Long followingId;

        public Follow toEntity(User follower, User following) {
            return Follow.builder()
                    .follower(follower)
                    .following(following)
                    .accept(false)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class AcceptGgamfReqDto {
        private Long followId;
        private Long userId;
    }

}
