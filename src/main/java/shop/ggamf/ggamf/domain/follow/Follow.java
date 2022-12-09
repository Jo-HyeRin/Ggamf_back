package shop.ggamf.ggamf.domain.follow;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.ggamf.ggamf.domain.AuditingTime;
import shop.ggamf.ggamf.domain.user.User;

@NoArgsConstructor
@Getter
@Table(name = "follow")
@Entity
public class Follow extends AuditingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean accept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    User following;

    @Builder
    public Follow(Long id, Boolean accept, User users, User follower, User following) {
        this.id = id;
        this.accept = accept;
        this.users = users;
        this.follower = follower;
        this.following = following;
    }

    // 겜프 수락
    public void acceptGgamf() {
        accept = true;
    }

}
