package shop.ggamf.ggamf.domain.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "room")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 20)
    private String gameName;

    @Column(nullable = false, length = 30)
    private String roomName;

    @Column(nullable = false)
    private Long totalPeople;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private GameCode gameCode;

    @Builder
    public Room(Long id, String gameName, String roomName, Long totalPeople, Boolean active, User user,
            GameCode gameCode) {
        this.id = id;
        this.gameName = gameName;
        this.roomName = roomName;
        this.totalPeople = totalPeople;
        this.active = active;
        this.user = user;
        this.gameCode = gameCode;
    }
}