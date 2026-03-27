package com.sn0w.suisei.api.core.domain.email;

import java.util.Map;

public class Email{
    private final Recipient recipient;
    private final Subject subject;
    private final Template template;
    private final Data data;
    private final Type type;


    private Email(
            Recipient recipient,
            Subject subject,
            Template template,
            Data data,
            Type type) {
        this.recipient = recipient;
        this.subject = subject;
        this.template = template;
        this.data = data;
        this.type = type;
    }

    public static Email otpMail(
            String recipient,
            String name,
            String otpCode,
            String expiryMinutes) {
        return new Email(
                Recipient.of(recipient),
                Subject.of("Suisei Verify Email"),
                Template.of("mail/otp-email"),
                Data.of(Map.of(
                        "name",          name,
                        "otpCode",       otpCode,
                        "expiryMinutes", expiryMinutes
                )),
                Type.OTP
        );
    }

    public static Email welcomeMail(
            String recipient,
            String name,
            String registeredAt
    ) {
        return new Email(
                Recipient.of(recipient),
                Subject.of("Welcome to suisei"),
                Template.of("mail/welcome-email"),
                Data.of(Map.of(
                        "name",          name,
                        "email",       recipient,
                        "registeredAt", registeredAt
                )),
                Type.WELCOME
        );
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public Subject getSubject() {
        return subject;
    }

    public Type getType() {
        return type;
    }

    public Template getTemplate() {
        return template;
    }

    public Data getData() {
        return data;
    }
}
