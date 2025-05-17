package br.univesp.pi3.service;

import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.dto.OrgDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMail(OrgDTO dto, ClienteDTO clienteDTO){
        var message = new SimpleMailMessage();
        message.setFrom("emailsenderteste2@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject("Agendamento " + clienteDTO.getCodAgendamento() +", confirmado.");
        message.setText("Agendamento com o cliente de ID " + clienteDTO.getClienteId() + " realizado às " + LocalDateTime.now());
        try {
            log.info("[E-MAIL SERVICE] - Enviando e-mail");
            mailSender.send(message);
            log.info("[E-MAIL SERVICE] - E-mail enviado com sucesso");
        } catch (MailException e) {
            log.error("[E-MAIL SERVICE] - Erro no envio do e-mail.");
            e.printStackTrace();
        }
    }

}
