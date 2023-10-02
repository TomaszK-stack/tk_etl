package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
import com.example.tk_etlproc.reading.sources.file.FileXLSXSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("config/file")
public class ReadingFileController {
    private FileCSVSource fileCSVSource;

    private FileXLSXSource fileXLSXSource;
    public ReadingFileController(FileCSVSource fileCSVSource, FileXLSXSource fileXLSXSource) {
        this.fileCSVSource = fileCSVSource;
        this.fileXLSXSource = fileXLSXSource;
    }


    @PostMapping("/csv")
    public ResponseEntity<String> fileCSVConfig(@RequestBody ConfigFileDTO configDTO) throws FileNotFoundException, StepNotFoundException {
        fileCSVSource.read(configDTO);
        return ResponseEntity.ok("");
    }
    @PostMapping("/xlsx")
    public ResponseEntity<String> fileXLSXConfig(@RequestBody ConfigFileDTO configDTO) throws IOException, StepNotFoundException {
        fileXLSXSource.read(configDTO);
        return ResponseEntity.ok("");
    }


}
