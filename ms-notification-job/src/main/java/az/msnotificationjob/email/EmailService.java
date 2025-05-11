package az.msnotificationjob.email;

import az.msnotificationjob.events.ApplicationEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static az.msnotificationjob.email.EmailTemplate.*;
import static java.nio.charset.StandardCharsets.*;
import static org.springframework.mail.javamail.MimeMessageHelper.*;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    @Async
    public void sentApplicationConfirmationEmail(
            ApplicationEvent applicationEvent

    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("mehreliyevelvin1@gmail.com");

        final String templateName = APPLICATION_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantName",applicationEvent.getApplicantName());
        variables.put("status",applicationEvent.getStatus());

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(templateName);

        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(applicationEvent.getApplicantMail());
            mailSender.send(mimeMessage);

            log.info(String.format("INFO - Email successfully sent: %s", applicationEvent.getApplicantMail()));
        }

        catch (MessagingException e) {
            log.warn("WARNING- Cannot send email to {}", applicationEvent.getApplicantMail(), e);
        }

    }



}
