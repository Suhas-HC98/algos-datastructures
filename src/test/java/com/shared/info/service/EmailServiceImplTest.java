package com.shared.info.service;

import com.shared.info.dto.Mail;
import com.shared.info.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    private final JavaMailSender mailSender = mock(JavaMailSender.class);
    private final EmailServiceImpl service = new EmailServiceImpl(mailSender);

    @Test
    void sendEmail() throws IOException {
        var messageArgumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        service.sendEmailWithoutAttachment(mail());

        verify(mailSender).send(messageArgumentCaptor.capture());
        assertEquals(mail().from(), messageArgumentCaptor.getValue().getFrom());
        assertEquals(mail().subject(), messageArgumentCaptor.getValue().getSubject());
        assertEquals(mail().recipients().get(0), requireNonNull(messageArgumentCaptor.getValue().getTo())[0]);
    }

    private Mail mail() throws IOException {
        File file = new File("src/main/resources/data/files/IpData_mock.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), fileInputStream);

        return Mail.builder().from("SYSTEM@SPRING.COM").subject("TEST").message("TEST EMAIL")
                .recipients(List.of("ATOM@SPRING@SPRING.COM")).file(multipartFile).build();
    }
}