package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.ConfigFileDTO;
import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;


@RestController
@RequestMapping("config")

public class ReadingFileController {
    private FileCSVSource fileCSVSource;

    public ReadingFileController(FileCSVSource fileCSVSource) {
        this.fileCSVSource = fileCSVSource;
    }

    @PostMapping("/file")
    public ResponseEntity<String> fileConfig(@RequestBody ConfigFileDTO configDTO) throws FileNotFoundException {
        fileCSVSource.read(configDTO);
        return ResponseEntity.ok("");
    }


}
