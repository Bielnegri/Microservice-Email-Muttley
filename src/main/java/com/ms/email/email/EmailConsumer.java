package com.ms.email.email;

import com.ms.email.email.dto.EventoEmail;
import com.ms.email.email.dto.InscricaoEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "email.inscricao.confirmada", groupId = "email-group")
    public void consumirConfirmacao(InscricaoEmail dto) {
        log.info("Processando confirmação: {}", dto.destinatario());
        emailService.enviarConfirmacaoCadastro(dto);
    }

    @KafkaListener(topics = "email.evento.cancelado", groupId = "email-group")
    public void consumirCancelado(EventoEmail dto) {
        log.info("Processando cancelamento: {}", dto.destinatario());
        emailService.enviarEventoCancelado(dto);
    }

    @KafkaListener(topics = "email.evento.concluido", groupId = "email-group")
    public void consumirConcluido(EventoEmail dto) {
        log.info("Processando conclusão: {}", dto.destinatario());
        emailService.enviarEventoConcluido(dto);
    }
}