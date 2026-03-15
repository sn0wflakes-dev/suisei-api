package com.sn0w.suisei.api.infra.email;

import com.sn0w.suisei.api.app.usecase.EmailUsecase;
import com.sn0w.suisei.api.core.domain.email.Email;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailGateway implements EmailUsecase {

    private static final Logger log = LogManager.getLogger(EmailGateway.class);

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${email.from}")
    private String sender;

    public EmailGateway(
            JavaMailSender javaMailSender,
            TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async("emailTaskExecutor")
    @Override
    public void send(Email email) {
        try {
            Context context = new Context();
            context.setVariables(email.getData().getData());
            String emailContent = templateEngine.process(email.getTemplate().getValue(), context);

            MimeMessage mime = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
            helper.setFrom(sender);
            helper.setTo(email.getRecipient().getValue());
            helper.setSubject(email.getSubject().getValue());
            helper.setText(emailContent, true);

            javaMailSender.send(mime);
        } catch (Exception e) {
            log.error("[ERROR:GATEWAY] Failed send email to  : {}, error details : {}",
                    email.getRecipient().getValue(),
                    e.getMessage());
        }
    }
}
