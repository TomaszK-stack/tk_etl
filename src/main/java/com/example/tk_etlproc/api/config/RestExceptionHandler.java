package com.example.tk_etlproc.api.config;

import com.example.tk_etlproc.db.log.entities.Log;
import com.example.tk_etlproc.db.log.repo.LogRepository;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private LogRepository logRepository;

    @Autowired
    public RestExceptionHandler(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    //    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Object> handleSqlErrors(SQLException exception){
//        return ResponseEntity.status(500).body(exception.getMessage());
//    }
    @ExceptionHandler(StepNotFoundException.class)
    public ResponseEntity<Object> handleStepNotFoundErrors(StepNotFoundException exception){
        exception.printStackTrace();
        Log log = new Log();
        log.setErrorMessage(exception.getMessage());
        logRepository.save(log);
        return ResponseEntity.status(500).body("Invalid step for processing data, full message:" + exception.getMessage());
    }
}
