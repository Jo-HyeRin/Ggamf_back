package shop.ggamf.ggamf.domain.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.user.User;

@NoArgsConstructor
@Getter
@Table(name = "report")
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitUser_id")
    private User submitUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badUser_id")
    private User badUser;

    @OneToOne(fetch = FetchType.LAZY)
    private ReasonCode reasonCode;

    @Builder
    public Report(Long id, String detail, User submitUser, User badUser, ReasonCode reasonCode) {
        this.id = id;
        this.detail = detail;
        this.submitUser = submitUser;
        this.badUser = badUser;
        this.reasonCode = reasonCode;
    }
}
