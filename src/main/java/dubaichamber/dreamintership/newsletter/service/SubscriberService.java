package dubaichamber.dreamintership.newsletter.service;

import dubaichamber.dreamintership.newsletter.entity.Subscriber;
import dubaichamber.dreamintership.newsletter.repository.SubscriberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final EmailService emailService;

    public void subscribe(String email) {
        if (subscriberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 구독 중인 이메일입니다.");
        }

        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(email);
        subscriber.setSubscribed(true);
        subscriber.setStatus(Subscriber.SubscriptionStatus.ACTIVE);
        subscriberRepository.save(subscriber);
    }

    public void unsubscribe(String email) {
        Subscriber subscriber = subscriberRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("구독자를 찾을 수 없습니다."));

        subscriber.setSubscribed(false);
        subscriber.setStatus(Subscriber.SubscriptionStatus.UNSUBSCRIBED);
        subscriberRepository.save(subscriber);
    }
}