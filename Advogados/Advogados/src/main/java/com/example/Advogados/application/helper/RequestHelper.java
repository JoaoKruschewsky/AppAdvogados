package com.example.Advogados.application.helper;

import com.example.Advogados.application.exception.RequestsException;
import com.example.Advogados.domains.Requests;
import org.springframework.http.HttpStatus;

import java.util.List;

public class RequestHelper {


    public static void ifContains(List<Requests> body) {

        if(body.isEmpty()){
            throw new RequestsException("No requests", HttpStatus.NOT_FOUND);
        }
    }
}
