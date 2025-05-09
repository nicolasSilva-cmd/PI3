package br.univesp.pi3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static br.univesp.pi3.fixture.ClienteFixture.getClienteDTO;
import static br.univesp.pi3.fixture.OrgFixture.getOrgDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    EmailService service;

    @Mock
    JavaMailSender mailSender;


    @Test
    void sendMail() {
        service.sendMail(getOrgDTO(), getClienteDTO());

        verify(mailSender, atLeastOnce()).send(any(SimpleMailMessage.class));

    }


}