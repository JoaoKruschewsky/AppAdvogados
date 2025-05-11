package com.example.Advogados.application.helper;

import com.example.Advogados.Model.Requests;
import com.example.Advogados.exception.RequestsException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RequestHelper {


    public static void ifContains(List<Requests> body) {

        if(body.isEmpty()){
            throw new RequestsException("No requests", HttpStatus.NOT_FOUND);
        }
    }
}
