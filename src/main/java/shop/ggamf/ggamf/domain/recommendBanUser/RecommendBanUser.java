package shop.ggamf.ggamf.domain.recommendBanUser;

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
@Table(name = "recommend_ban_user")
@Entity
public class RecommendBanUser extends AuditingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banuser_id")
    User banuser;

    @Builder
    public RecommendBanUser(Long id, User users, User banuser) {
        this.id = id;
        this.users = users;
        this.banuser = banuser;
    }
}
