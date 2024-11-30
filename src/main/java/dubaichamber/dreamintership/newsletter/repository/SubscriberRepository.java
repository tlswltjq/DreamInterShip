package dubaichamber.dreamintership.newsletter.repository;

import dubaichamber.dreamintership.newsletter.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
    boolean existsByEmail(String email);

    List<Subscriber> findByIsSubscribedTrue();

    List<Subscriber> findByStatus(Subscriber.SubscriptionStatus status);
}