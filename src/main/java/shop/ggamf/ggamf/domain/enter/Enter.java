package shop.ggamf.ggamf.domain.enter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.ggamf.ggamf.domain.AuditingTime;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "enter")
@Entity
public class Enter extends AuditingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Column(nullable = false)
    private Boolean stay;

    @Column(nullable = false)
    private Boolean stayUntilEnd;

    @Builder
    public Enter(Long id, User user, Room room, Boolean stay, Boolean stayUntilEnd) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.stay = stay;
        this.stayUntilEnd = stayUntilEnd;
    }

    // @Builder
    // public Enter(Long id, User user, Room room, Boolean stay, Boolean
    // stayUntilEnd, LocalDateTime createdAt,
    // LocalDateTime updatedAt) {
    // this.id = id;
    // this.user = user;
    // this.room = room;
    // this.stay = stay;
    // this.stayUntilEnd = stayUntilEnd;
    // this.createdAt = createdAt;
    // this.updatedAt = updatedAt;
    // }

    // 파티방 나가기
    public void notStayRoom() {
        stay = false;
    }
}