package com.ms.email.email.dto;

public record EventoEmail(
        String destinatario,
        String nome,
        String tema,
        String data
) {}
