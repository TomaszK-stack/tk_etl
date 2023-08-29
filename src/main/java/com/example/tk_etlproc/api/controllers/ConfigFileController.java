package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.ConfigFileDTO;
import com.example.tk_etlproc.reading.InputHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ConfigFileController {
    private InputHandler inputHandler;

    public ConfigFileController(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }


    @PostMapping("config")
    public ResponseEntity<String> postConfig(@RequestBody ConfigFileDTO configDTO) throws FileNotFoundException {
      return   ResponseEntity.ok(inputHandler.handle_data(configDTO));

    }

}
