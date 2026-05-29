package com.ms.email.email.dto;

import java.time.LocalDate;

public record CertificadoEmail(
        String destinatario,
        String nome,
        String tema,
        LocalDate dataEvento,
        LocalDate dataEmissao,
        String url
) {}
