package shop.ggamf.ggamf.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingTime {

    // 수정시간
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    // 생성시간
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

}
