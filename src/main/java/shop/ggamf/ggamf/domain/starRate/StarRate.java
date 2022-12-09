package shop.ggamf.ggamf.domain.starRate;

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
import shop.ggamf.ggamf.domain.user.User;

@NoArgsConstructor
@Getter
@Table(name = "star_rate")
@Entity
public class StarRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="giver_id")
    private User giver;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="receiver_id")
    private User receiver;
    private Long rate;

    @Builder
    public StarRate(Long id, User giver, User receiver, Long rate) {
        this.id = id;
        this.giver = giver;
        this.receiver = receiver;
        this.rate = rate;
    }
}
