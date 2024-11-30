package dubaichamber.dreamintership.newsletter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subscribers")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber {

    @Id
    @Column(nullable = false)
    private String email;

    @Column(name = "is_subscribed", nullable = false)
    private boolean isSubscribed = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime subscribedAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum SubscriptionStatus {
        ACTIVE, UNSUBSCRIBED
    }

    @PrePersist
    protected void onCreate() {
        subscribedAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}