package com.example.tk_etlproc.api.config;

import com.example.tk_etlproc.exceptions.StepNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Object> handleSqlErrors(SQLException exception){
//        return ResponseEntity.status(500).body(exception.getMessage());
//    }
//    @ExceptionHandler(StepNotFoundException.class)
//    public ResponseEntity<Object> handleStepNotFoundErrors(StepNotFoundException exception){
//        return ResponseEntity.status(500).body("Invalid step for processing data");
//    }
}
