package com.shared.info.controller;

import com.shared.info.controller.documentation.EmailControllerDocumentation;
import com.shared.info.dto.Mail;
import com.shared.info.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
@AllArgsConstructor
public class EmailController implements EmailControllerDocumentation {

    private final EmailService service;

    @PostMapping(value = "/send")
    public void sendEmailWithoutAttachment(@RequestBody Mail mail) {
        service.sendEmailWithoutAttachment(mail);
    }

    @PostMapping(value = "/send-email-with-attachment")
    public void sendEmailWithAttachment(@ModelAttribute Mail mail) throws MessagingException {
        service.sendEmailWithAttachment(mail);
    }
}
