package shop.ggamf.ggamf.domain.gameCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "game_code")
@Entity
public class GameCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logo;

    @Column(unique = true, nullable = false, length = 20)
    private String gameName;

    @Builder
    public GameCode(Long id, String logo, String gameName) {
        this.id = id;
        this.logo = logo;
        this.gameName = gameName;
    }

    public void 로고수정(String logo) {
        this.logo = logo;
    }

    public void 게임이름수정(String gameName) {
        this.gameName = gameName;
    }
}