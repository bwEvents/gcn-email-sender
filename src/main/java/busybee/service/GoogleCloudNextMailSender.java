package busybee.service;

import busybee.dto.EmailSentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Slf4j
@Service
public class GoogleCloudNextMailSender {
    public static final String NOREPLY_BWEVENTSTECH_COM = "noreply@bweventstech.com";
    public static final String THE_GOOGLE_CLOUD_SPONSORSHIP_TEAM = "The Google Cloud Sponsorship Team";
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

    private MimeMessage createMessageFrom(String invitee, String invitationLink, String supportEmail, String emailTo) throws MessagingException, UnsupportedEncodingException {

        //decode base64 invitation link
        if (!invitationLink.startsWith("http")) {
            invitationLink = new String(Base64.getDecoder().decode(invitationLink));
        }

        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMailMessage,"utf-8");
        helper.setFrom(new InternetAddress(NOREPLY_BWEVENTSTECH_COM, THE_GOOGLE_CLOUD_SPONSORSHIP_TEAM));
        helper.setReplyTo(NOREPLY_BWEVENTSTECH_COM);
        helper.setTo(emailTo);
        helper.setSubject(getSubjectFrom(invitee));
        helper.setText(getBodyFrom(invitee,invitationLink,supportEmail),true);
        return mimeMailMessage;
    }

    private String getBodyFrom(String invitee, String invitationLink, String supportEmail) {

    return "<div style=\"font-family:'Helvetica Neue','Helvetica','Arial',sans-serif;font-size:14px;color:#333\">\n" +
            "<table width=\"100%\"><tbody><tr>\n" +
            "<td align=\"center\">\n" +
            "            \n" +
            "                <div style=\"color:#333333;display:none;font-size:1px;line-height:1px;max-height:0;max-width:0;opacity:0;overflow:hidden\">You've been invited by "+invitee+" to register for Google Cloud Next '23</div>\n" +
            "                <div style=\"display:none;max-height:0;overflow:hidden\">&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C<wbr>&nbsp;\u200C&nbsp;&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;<wbr>&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;\u200C&nbsp;</div>\n" +
            "            <table width=\"600\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr></tr></tbody></table>\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"padding:15px\"><tbody><tr>\n" +
            "<td>\n" +
            "\n" +
            "                        <table width=\"100%\" cellspacing=\"0\" style=\"padding-top:10px;padding-bottom:10px\"><tbody><tr><td align=\"left\"><a><img src=\"https://ci5.googleusercontent.com/proxy/Zh7dz39UWwtjxtJpVKKmQoDFz8wkmjDcaMGgRDo3BT5D92uHdQwpLY43kUsXPhB6x7mF6DOgZ7RdJKc7vxrTilRgzQ63tkfA3mv8cuKCKJqO8XQCHg=s0-d-e1-ft#https://assets.swoogo.com/uploads/medium/1951390-62ea82ee49307.png\" width=\"198\" alt=\"\" style=\"max-width:198px\" class=\"CToWUd a6T\" data-bit=\"iit\" tabindex=\"0\"><div class=\"a6S\" dir=\"ltr\" style=\"opacity: 0.01; left: 169px; top: 594.297px;\"><div id=\":c1\" class=\"T-I J-J5-Ji aQv T-I-ax7 L3 a5q\" title=\"Download\" role=\"button\" tabindex=\"0\" aria-label=\"Download attachment \" jslog=\"91252; u014N:cOuCgd,Kr2w4b,xr6bB\" data-tooltip-class=\"a1V\"><div class=\"akn\"><div class=\"aSK J-J5-Ji aYr\"></div></div></div></div></a></td></tr></tbody></table>\n" +
            "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr>\n" +
            "<td>\n" +
            "            <h1>Google Cloud<br>\n" +
            "Next&nbsp;’23</h1>        </td>\n" +
            "    </tr></tbody></table>\n" +
            "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr>\n" +
            "<td>\n" +
            "            <h3><span style=\"font-size:18px\">You've been invited by "+invitee+" to register for Google Cloud Next '23</span></h3>        </td>\n" +
            "    </tr></tbody></table>\n" +
            "<table cellspacing=\"0\"><tbody><tr>\n" +
            "<td height=\"20\"></td>\n" +
            "</tr></tbody></table>\n" +
            "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody><tr>\n" +
            "<td>\n" +
            "            <p style=\"margin-top:0;margin-bottom:10px\">Join us for Google Cloud Next ’23. Together we’ll explore how to adopt and integrate the latest technologies into your business. Engage with leaders and experts who are using Google Cloud to drive growth and power success. Be part of the community that’s shaping the future of cloud.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">As part of your Next sponsorship, we’re pleased to invite you to register for the Full Conference Pass at Google Cloud Next ’23.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">Click <a href=\""+invitationLink+"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q="+invitationLink+"\">here</a> to register now.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">If you believe that you should be assigned a different pass type, please email&nbsp;your sponsor manager.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">To ensure that you receive the discount for your registration please use <a href=\"mailto:"+supportEmail+"\" target=\"_blank\">"+supportEmail+"</a> to complete your registration.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">If you have any questions, please email&nbsp;your sponsor manager.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">Thank you for your continued support and success as a Google Cloud partner. We look forward to seeing you there.</p>\n" +
            "\n" +
            "<p style=\"margin-top:0;margin-bottom:10px\">The Google Cloud Sponsorship Team</p>        </td>\n" +
            "    </tr></tbody></table>\n" +
            "<table cellspacing=\"0\"><tbody><tr>\n" +
            "<td height=\"20\"></td>\n" +
            "</tr></tbody></table>\n" +
            "</td>\n" +
            "                </tr></tbody></table>\n" +
            "</td>\n" +
            "    </tr></tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
            "</div></div>";
    }

    private String getSubjectFrom(String invitee) {
        return "You've been invited by " + invitee + " to register for Google Cloud Next '23";
    }
}
