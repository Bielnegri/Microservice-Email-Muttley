package com.ms.email.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.email.email.dto.CertificadoEmail;
import com.ms.email.email.dto.EventoEmail;
import com.ms.email.email.dto.InscricaoEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "email.inscricao.confirmada", groupId = "email-group")
    public void consumirConfirmacao(String payload) throws JsonProcessingException {
        InscricaoEmail dto = objectMapper.readValue(payload, InscricaoEmail.class);

        log.info("Processando confirmação: {}", dto.destinatario());
        emailService.enviarConfirmacaoCadastro(dto);

    }

    @KafkaListener(topics = "email.evento.cancelado", groupId = "email-group")
    public void consumirCancelado(String payload) throws JsonProcessingException {

        EventoEmail dto = objectMapper.readValue(payload, EventoEmail.class);

        log.info("Processando cancelamento: {}", dto.destinatario());
        emailService.enviarEventoCancelado(dto);
    }

    @KafkaListener(topics = "email.evento.concluido", groupId = "email-group")
    public void consumirConcluido(String payload) throws JsonProcessingException {
        EventoEmail dto = objectMapper.readValue(payload, EventoEmail.class);

        log.info("Processando conclusão: {}", dto.destinatario());
        emailService.enviarEventoConcluido(dto);
    }

    @KafkaListener(topics = "email.certificado", groupId = "email-group")
    public void consumirCertificados(String payload) throws JsonProcessingException {
        CertificadoEmail dto = objectMapper.readValue(payload, CertificadoEmail.class);

        log.info("Processando envio de certificado: {}", dto.destinatario());
        emailService.enviarCertificados(dto);
    }
}