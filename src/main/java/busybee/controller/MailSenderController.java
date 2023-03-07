package busybee.controller;

import busybee.configuration.AppProperties;
import busybee.dto.EmailSentInfo;
import busybee.service.GoogleCloudNextMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailSenderController {

    private final GoogleCloudNextMailSender mailSender;
    private final AppProperties appProperties;


    public MailSenderController(GoogleCloudNextMailSender mailSender, AppProperties appProperties) {
        this.mailSender = mailSender;
        this.appProperties = appProperties;
    }

    @CrossOrigin
    @GetMapping("/sendEmail")
    public EmailSentInfo sendMail(@RequestParam String invitee, @RequestParam String invitationLink,@RequestParam String supportEmail,
                                  @RequestParam String emailTo, @RequestHeader("auth-code") String authCode) {
        if (!appProperties.getAuthCode().equals(authCode)) {
            return EmailSentInfo
                    .builder()
                    .errorMessage("Invalid auth code.")
                    .build();
        }

        return mailSender.sendMail(invitee,invitationLink,supportEmail,emailTo);
    }
}
