package dubaichamber.dreamintership;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String landingPage() {
        return "landingPage";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }
}
