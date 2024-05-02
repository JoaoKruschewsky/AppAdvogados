package com.example.Advogados.Model.Enum;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum RelationEnum {
    Em_Andamento("Em andamento"),
    Concluido("Concluido"),
    Cancelado("Cancelado"),
    Aguardando("Aguardando");

    private final String relation;

    RelationEnum(String relation) {
        // TODO Auto-enerated constructor stub
        this.relation = relation;
    }

    public String getRelation() {
        return this.relation;
    }
}
