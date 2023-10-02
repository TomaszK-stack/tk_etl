//package com.example.tk_etlproc;
//
//import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
//import com.example.tk_etlproc.processing.steps.BaseStep;
//import com.example.tk_etlproc.processing.steps.BaseStepMeta;
//import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class MyCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    private FileCSVSource fileCSVSource;
//
//    @Override
//    public void run(String... args) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ConfigProcessingDTO configProcessingDTO = new ConfigProcessingDTO();
//        Map<String, BaseStepMeta> map = new HashMap<>();
//        map.put("nullif", new BaseStepMeta());
//
//        configProcessingDTO.setConfigProcessmap((HashMap<String, BaseStepMeta>) map);
//        String test = objectMapper.writeValueAsString(configProcessingDTO);
//        System.out.println(test);
//    }
////    @Override
////    public void run(String... args) throws Exception {
////        fileSource.read("C:\\Users\\korni\\OneDrive\\Dokumenty\\insurance.csv");
////    }
//}
