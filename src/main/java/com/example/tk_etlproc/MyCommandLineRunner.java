package com.example.tk_etlproc;

import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class MyCommandLineRunner implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        throw new StepNotFoundException("Test");
//        System.out.println("asdas");
    }

//    @Override
//    public void run(String... args) throws Exception {
//        fileSource.read("C:\\Users\\korni\\OneDrive\\Dokumenty\\insurance.csv");
//    }
}
