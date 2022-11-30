package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * MailServiceImpl is implemented MailService.
 *
 * @author Oksana Borisenko
 */

@Service
public class MailSenderImpl implements MailSender {
    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    @Autowired
    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send method message
     *
     * @param emailTo
     * @param subject
     * @param massage
     */
    public void send(String emailTo, String subject, String massage){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(massage);

        mailSender.send(mailMessage);
    }
}
