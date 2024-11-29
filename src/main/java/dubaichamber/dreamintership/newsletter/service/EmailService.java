package dubaichamber.dreamintership.newsletter.service;

import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

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
}
