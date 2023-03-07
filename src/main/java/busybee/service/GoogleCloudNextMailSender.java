package busybee.service;

import busybee.dto.EmailSentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Base64;

@Slf4j
@Service
public class GoogleCloudNextMailSender {
    public static final String NOREPLY_BWEVENTSTECH_COM = "noreply@bweventstech.com";
    private final JavaMailSender javaMailSender;

    public GoogleCloudNextMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public EmailSentInfo sendMail(String invitee, String invitationLink,String supportEmail,String emailTo) {
        log.info("Sending mail");
        try {

            MimeMessage message = createMessageFrom(invitee,invitationLink,supportEmail,emailTo);
            javaMailSender.send(message);

        return EmailSentInfo.builder()
                .mailSentSuccessfully(true)
                .build();
        } catch (Exception e) {
            log.error("Failed to send mail {}", e.getMessage());
            return EmailSentInfo.builder()
                    .mailSentSuccessfully(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    private MimeMessage createMessageFrom(String invitee, String invitationLink, String supportEmail, String emailTo) throws MessagingException {

        //decode base64 invitation link
        if (!invitationLink.startsWith("http")) {
            invitationLink = new String(Base64.getDecoder().decode(invitationLink));
        }

        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMailMessage,"utf-8");
        helper.setFrom(NOREPLY_BWEVENTSTECH_COM);
        helper.setReplyTo(NOREPLY_BWEVENTSTECH_COM);
        helper.setTo(emailTo);
        helper.setSubject(getSubjectFrom(invitee));
        helper.setText(getBodyFrom(invitee,invitationLink,supportEmail),true);
        return mimeMailMessage;
    }

    private String getBodyFrom(String invitee, String invitationLink, String supportEmail) {

    return "<div align=\"center\">\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" style=\"width:6.25in\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"padding:11.25pt 11.25pt 11.25pt 11.25pt\">\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100.0%\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"padding:7.5pt .75pt 7.5pt .75pt\">\n" +
            "<p class=\"MsoNormal\"><img border=\"0\" width=\"198\" style=\"width:2.0625in\" id=\"m_3877703817395152821m_8144242891709996236_x0000_i1025\" src=\"https://ci5.googleusercontent.com/proxy/Zh7dz39UWwtjxtJpVKKmQoDFz8wkmjDcaMGgRDo3BT5D92uHdQwpLY43kUsXPhB6x7mF6DOgZ7RdJKc7vxrTilRgzQ63tkfA3mv8cuKCKJqO8XQCHg=s0-d-e1-ft#https://assets.swoogo.com/uploads/medium/1951390-62ea82ee49307.png\" class=\"CToWUd\" data-bit=\"iit\"><u></u><u></u></p>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<p class=\"MsoNormal\"><span style=\"display:none\"><u></u>&nbsp;<u></u></span></p>\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100.0%\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"padding:0in 0in 0in 0in\">\n" +
            "<h1>Google Cloud<br>\n" +
            "Next&nbsp;’23<u></u><u></u></h1>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<p class=\"MsoNormal\"><span style=\"display:none\"><u></u>&nbsp;<u></u></span></p>\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100.0%\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"padding:0in 0in 0in 0in\">\n" +
            "<h3>You've been invited by "+ invitee +" to register for Google Cloud Next '23<u></u><u></u></h3>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<p class=\"MsoNormal\"><span style=\"display:none\"><u></u>&nbsp;<u></u></span></p>\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "<tbody>\n" +
            "<tr style=\"height:15.0pt\">\n" +
            "<td style=\"padding:.75pt .75pt .75pt .75pt;height:15.0pt\"></td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<p class=\"MsoNormal\"><span style=\"display:none\"><u></u>&nbsp;<u></u></span></p>\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100.0%\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"padding:0in 0in 0in 0in\">\n" +
            "<p style=\"margin-right:0in;margin-bottom:7.5pt;margin-left:0in\">\n" +
            "Please take a moment and register for this event by clicking the link below. Failure to do so in a timely manner may affect your ability to attend this event.<u></u><u></u></p>\n" +
            "<p style=\"margin-right:0in;margin-bottom:7.5pt;margin-left:0in\">\n" +
            "<a href=\""+invitationLink+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q="+invitationLink+"&amp;source=gmail&amp;ust=1678255858880000&amp;usg=AOvVaw0wwLoFZ7VjXlaIS6xK3eU9\">"+invitationLink+"</a><u></u><u></u></p>\n" +
            "<p style=\"margin-right:0in;margin-bottom:7.5pt;margin-left:0in\">\n" +
            "If you have any questions, please email&nbsp;<a href=\"mailto:"+supportEmail+"\" target=\"_blank\">"+supportEmail+"</a>.<u></u><u></u></p>\n" +
            "<p style=\"margin-right:0in;margin-bottom:7.5pt;margin-left:0in\">\n" +
            "Thank you,<u></u><u></u></p>\n" +
            "<p style=\"margin-right:0in;margin-bottom:7.5pt;margin-left:0in\">\n" +
            "The Google Cloud Sponsorship Team<u></u><u></u></p>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "<p class=\"MsoNormal\"><span style=\"display:none\"><u></u>&nbsp;<u></u></span></p>\n" +
            "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "<tbody>\n" +
            "<tr style=\"height:15.0pt\">\n" +
            "<td style=\"padding:.75pt .75pt .75pt .75pt;height:15.0pt\"></td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>\n" +
            "</div>";
    }

    private String getSubjectFrom(String invitee) {
        return "You've been invited by " + invitee + " to register for Google Cloud Next '23";
    }
}
