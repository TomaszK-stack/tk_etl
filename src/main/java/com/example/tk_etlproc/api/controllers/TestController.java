package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.Test2Dto;
import com.example.tk_etlproc.api.DTO.TestDTO;
import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.api.config.SQLLogging.RequestLogContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {


    @GetMapping("")
    public void test(@RequestBody ConfigProcessingDTO testDTO){
        System.out.println(RequestLogContext.getRequestLog());
        RequestLogContext.setRequestLog("Mariusz Korszun");

    }
}
