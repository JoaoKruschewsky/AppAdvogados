package com.example.Advogados.application.enums;

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
