package com.shared.info.service.impl;

import com.shared.info.dto.Mail;
import com.shared.info.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailWithoutAttachment(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail.from());
        mailMessage.setSubject(mail.subject());
        mailMessage.setTo(mail.recipients().toArray(new String[0]));
        mailMessage.setText(mail.message());

        mailSender.send(mailMessage);
        log.info("Email sent successfully without attachment at {}", LocalDateTime.now());
    }

    @Override
    public void sendEmailWithAttachment(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(mail.from());
        messageHelper.setSubject(mail.subject());
        messageHelper.setTo(mail.recipients().toArray(new String[0]));
        messageHelper.setText(mail.message());
        messageHelper.addAttachment(mail.file().getOriginalFilename(), mail.file());

        mailSender.send(message);
        log.info("Email sent successfully with attachment at {}", LocalDateTime.now());
    }
}
