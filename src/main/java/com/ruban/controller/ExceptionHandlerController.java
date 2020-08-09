package com.ruban.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController extends DefaultHandlerExceptionResolver {

    @ExceptionHandler
    void handleException(Throwable e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
