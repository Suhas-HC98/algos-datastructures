package com.shared.info.controller;

import com.shared.info.dto.Mail;
import com.shared.info.service.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class EmailControllerTest {

    private final EmailService service = mock(EmailService.class);
    private final EmailController controller = new EmailController(service);

    @Test
    void should_send_email_and_print_log_when_invoked() {
        doNothing().when(service).sendEmailWithoutAttachment(mail());
        controller.sendEmailWithoutAttachment(mail());
        verify(service).sendEmailWithoutAttachment(mail());
    }

    @Test
    void sendEmailWithAttachment() throws MessagingException {
        doNothing().when(service).sendEmailWithAttachment(mail());
        controller.sendEmailWithAttachment(mail());
        verify(service).sendEmailWithAttachment(mail());
    }

    private Mail mail() {
        return Mail.builder().from("SYSTEM@SPRING.COM").subject("TEST").message("TEST EMAIL")
                .recipients(List.of("ATOM@SPRING@SPRING.COM")).file(null).build();
    }
}