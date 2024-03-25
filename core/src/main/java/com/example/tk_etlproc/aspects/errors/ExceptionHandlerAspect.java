package com.example.tk_etlproc.aspects.errors;

import com.example.tk_etlproc.archive.manager.ArchiveManager;
import com.example.tk_etlproc.db.log.entities.ArchiveLog;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandlerAspect {

    private ArchiveManager manager;

    @Autowired
    public ExceptionHandlerAspect(ArchiveManager manager) {
        this.manager = manager;
    }

    @AfterThrowing(pointcut = "execution(* com.example.tk_etlproc.reading.*.*(..))", throwing = "ex")
    public void handleException(Exception ex) {
        manager.update(ex.getMessage(), -1);
    }

}
