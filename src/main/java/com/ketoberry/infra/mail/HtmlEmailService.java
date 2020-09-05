package com.ketoberry.infra.mail;

import com.ketoberry.infra.config.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Profile({"local", "dev"})
@Component
@RequiredArgsConstructor
@Slf4j
public class HtmlEmailService implements EmailService {

    private final JavaMailSender javaMailSender;
    private final AppProperties appProperties;

    @Override
    public void sendEmail(Emailmessage emailmessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailmessage.getTo());
            mimeMessageHelper.setFrom(appProperties.getMailFrom(), "키토베리");
            mimeMessageHelper.setSubject(emailmessage.getSubject());
            mimeMessageHelper.setText(emailmessage.getMessage(), true);
            javaMailSender.send(mimeMessage);

            log.info("sent email : {}", emailmessage.getMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("failed to send email", e);
            throw new RuntimeException(e);
        }
    }
}
