package dubaichamber.dreamintership.newsletter.controller;

import dubaichamber.dreamintership.newsletter.dto.EmailMessage;
import dubaichamber.dreamintership.newsletter.service.EmailService;
import dubaichamber.dreamintership.newsletter.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final SubscriberService subscriberService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam String email) {
        subscriberService.subscribe(email);
        return ResponseEntity.ok("Subscription successful");
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam String email) {
        subscriberService.unsubscribe(email);
        return ResponseEntity.ok("Unsubscription successful");
    }

    @PostMapping("/newsletter/send")
    public ResponseEntity<String> sendNewsletter(@RequestBody EmailMessage emailTemplate) {
        emailService.sendToAllSubscribers(emailTemplate);
        return ResponseEntity.ok("Newsletter sent successfully to all subscribers");
    }
}