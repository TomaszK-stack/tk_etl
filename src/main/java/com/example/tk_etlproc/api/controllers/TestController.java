package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.TestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("")
    public String test(@RequestBody TestDTO testDTO){
        System.out.println(testDTO.isTest());
        return "TAAAAAKK JEEEEST !!!";
    }
}
