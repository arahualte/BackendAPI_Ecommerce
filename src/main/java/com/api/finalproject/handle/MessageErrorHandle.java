package com.api.finalproject.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.api.finalproject.exceptions.*;

@ControllerAdvice
public class MessageErrorHandle {
    @ResponseBody
    @ExceptionHandler({ UserException.class, ProductException.class, CartException.class, OrderException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage messageErrorHandle(Exception e) {
        return ErrorMessage.of(e.getMessage());
    }
}