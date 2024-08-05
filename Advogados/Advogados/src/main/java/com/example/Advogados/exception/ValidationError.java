package com.example.Advogados.infra;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends FieldMessage {

    private List<FieldMessage> err = new ArrayList<>();

    public void addErr(String fieldName, String message) {
        err.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getErr() {
        return err;
    }
}
