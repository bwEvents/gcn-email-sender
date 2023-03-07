package busybee.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@Data
public class AppProperties {
    /*
        Swoogo
     */
    public static final String SWOGOO_BASE_URI = "https://api.swoogo.com/api/v1";
    public static final String ALL_REGISTRANTS_RESPONSE_FIELD = "*";

    @PostConstruct
    public void log() {
        log.info("Starting with settings {}", this);
    }
}
