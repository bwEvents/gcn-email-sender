package busybee.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final String LAST_MODIFIED_DATE = "Last modified date:" + "Monday March 6 2023";
    @GetMapping("/health/status")
    public String getHealth() {
        return LAST_MODIFIED_DATE;
    }
}
