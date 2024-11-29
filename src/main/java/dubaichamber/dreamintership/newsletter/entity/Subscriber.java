package dubaichamber.dreamintership.newsletter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscribers")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber {

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean isSubscribed = true;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime subscribedAt;

    @LastModifiedDate
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