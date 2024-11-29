package dubaichamber.dreamintership.newsletter.service;

import dubaichamber.dreamintership.newsletter.entity.Subscriber;
import dubaichamber.dreamintership.newsletter.SubscriberRepository;
import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        subscriberRepository.save(subscriber);

        sendWelcomeEmail(email);
    }

    public void unsubscribe(String email) {
        Subscriber subscriber = subscriberRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("구독자를 찾을 수 없습니다."));

        subscriber.setSubscribed(false);
        subscriber.setStatus(Subscriber.SubscriptionStatus.UNSUBSCRIBED);
        subscriberRepository.save(subscriber);
    }

    private void sendWelcomeEmail(String email) {
        EmailMessage welcomeEmail = EmailMessage.builder()
                .to(email)
                .subject("Welcome to Dubai Chamber Newsletter")
                .message("<h1>구독해 주셔서 감사합니다</h1>")
                .build();

        emailService.sendHtmlMessage(welcomeEmail);
    }

    public List<Subscriber> getActiveSubscribers() {
        return subscriberRepository.findByIsSubscribedTrue();
    }
}