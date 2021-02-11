package com.salesken.ai.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Error {
    private int code;
    private String message;

    public static Error builder(int code, String message){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }
}
