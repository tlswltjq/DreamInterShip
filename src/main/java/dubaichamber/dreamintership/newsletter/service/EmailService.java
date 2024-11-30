package dubaichamber.dreamintership.newsletter.service;

import dubaichamber.dreamintership.newsletter.entity.Subscriber;
import dubaichamber.dreamintership.newsletter.repository.SubscriberRepository;
import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    private final SubscriberRepository subscriberRepository;

    public void sendHtmlMessage(EmailMessage emailMessage) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getMessage(), true);

            emailSender.send(message);
            log.info("HTML email sent successfully to: {}", emailMessage.getTo());

        } catch (Exception e) {
            log.error("Failed to send HTML email: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send HTML email", e);
        }
    }

    public void sendToAllSubscribers(EmailMessage emailTemplate) {
        List<Subscriber> activeSubscribers = subscriberRepository.findByIsSubscribedTrue();

        for (Subscriber subscriber : activeSubscribers) {
            EmailMessage personalizedEmail = EmailMessage.builder()
                    .to(subscriber.getEmail())
                    .subject(emailTemplate.getSubject())
                    .message(emailTemplate.getMessage())
                    .build();

            sendHtmlMessage(personalizedEmail);
        }

        log.info("Bulk email sent to {} subscribers", activeSubscribers.size());
    }
}