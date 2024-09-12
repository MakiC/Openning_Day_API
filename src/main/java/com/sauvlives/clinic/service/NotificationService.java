package com.sauvlives.clinic.service;

import com.sauvlives.clinic.entity.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    public void send(Validation validation){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("no-reply@Sauvlives.com");
        message.setTo(validation.getUser().getEmail());
        String textBlock="""
                Bonjour %s,
                Votre code d'activation est %s.
                """;

        String corps=String.format(textBlock,
                validation.getUser().getFirstName(),
                validation.getActivationCode());

        message.setText(corps);
        javaMailSender.send(message);

    }
}
