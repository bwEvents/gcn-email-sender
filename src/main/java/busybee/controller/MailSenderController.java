package busybee.controller;

import busybee.dto.EmailSentInfo;
import busybee.service.GoogleCloudNextMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailSenderController {

    private final GoogleCloudNextMailSender mailSender;


    public MailSenderController(GoogleCloudNextMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/sendEmail")
    public EmailSentInfo sendMail(@RequestParam String invitee, @RequestParam String invitationLink,@RequestParam String supportEmail,
                                  @RequestParam String emailTo) {
        return mailSender.sendMail(invitee,invitationLink,supportEmail,emailTo);
    }
}
