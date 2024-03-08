package com.example.Advogados.message;

import org.springframework.stereotype.Component;

@Component
public class message {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
