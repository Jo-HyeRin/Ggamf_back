package shop.ggamf.ggamf.domain.agree;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "agree")
@Entity
public class Agree { //User엔티티에 넣기...! 개인정보제공동의만 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Boolean terms;
    private Timestamp termsAt;
    private Boolean personal;
    private Timestamp personalAt;
    private Boolean event;
    private Timestamp eventAt;
}
