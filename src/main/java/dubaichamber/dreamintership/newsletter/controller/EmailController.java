package dubaichamber.dreamintership.newsletter.controller;

import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import dubaichamber.dreamintership.newsletter.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/test/simple")
    public ResponseEntity<String> testSimpleEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendSimpleMessage(emailMessage);
        return ResponseEntity.ok("Simple email sent successfully");
    }

    @PostMapping("/test/html")
    public ResponseEntity<String> testHtmlEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendHtmlMessage(emailMessage);
        return ResponseEntity.ok("HTML email sent successfully");
    }

    @GetMapping("/test/preset")
    public ResponseEntity<String> sendPresetTestEmail(@RequestParam String to) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(to)
                .subject("Test Email from Spring Boot")
                .message("""
                        <html>
                            <body>
                                <h1>Test Email</h1>
                                <p>This is a test email from your Spring Boot application.</p>
                                <ul>
                                    <li>Feature 1</li>
                                    <li>Feature 2</li>
                                    <li>Feature 3</li>
                                </ul>
                                <p>Best regards,<br/>Your Application</p>
                            </body>
                        </html>
                        """)
                .build();

        emailService.sendHtmlMessage(emailMessage);
        return ResponseEntity.ok("Preset test email sent successfully");
    }
}
