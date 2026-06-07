package com.ms.email.email.dto;

public record CredenciaisEmail(
        String destinatario,
        String nome,
        String user,
        String senha
) {}
