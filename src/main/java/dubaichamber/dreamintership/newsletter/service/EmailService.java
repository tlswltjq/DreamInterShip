package dubaichamber.dreamintership.newsletter.service;

import dubaichamber.dreamintership.newsletter.entity.Subscriber;
import dubaichamber.dreamintership.newsletter.repository.SubscriberRepository;
import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
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

    public void sendSimpleMessage(EmailMessage emailMessage) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailMessage.getTo());
            message.setSubject(emailMessage.getSubject());
            message.setText(emailMessage.getMessage());

            emailSender.send(message);
            log.info("Email sent successfully to: {}", emailMessage.getTo());

        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

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
        List<Subscriber> activeSubscribers = subscriberRepository.findBySubscribedTrue();

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

    public void sendToAllSubscribersBatch(EmailMessage emailTemplate, int batchSize) {
        List<Subscriber> activeSubscribers = subscriberRepository.findBySubscribedTrue();
        int totalSubscribers = activeSubscribers.size();

        for (int i = 0; i < totalSubscribers; i += batchSize) {
            List<Subscriber> batch = activeSubscribers.subList(
                    i, Math.min(i + batchSize, totalSubscribers));

            for (Subscriber subscriber : batch) {
                EmailMessage personalizedEmail = EmailMessage.builder()
                        .to(subscriber.getEmail())
                        .subject(emailTemplate.getSubject())
                        .message(emailTemplate.getMessage())
                        .build();

                sendHtmlMessage(personalizedEmail);
            }

            log.info("Processed batch {}/{}",
                    Math.min(i + batchSize, totalSubscribers), totalSubscribers);

            try {
                // 배치 처리 사이에 잠시 대기하여 메일 서버 부하 방지
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Batch processing interrupted", e);
            }
        }

        log.info("Batch email sending completed. Total subscribers processed: {}",
                totalSubscribers);
    }
}