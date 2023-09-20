package com.example.tk_etlproc;

import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private FileCSVSource fileCSVSource;

    @Override
    public void run(String... args) throws Exception {

    }
//    @Override
//    public void run(String... args) throws Exception {
//        fileSource.read("C:\\Users\\korni\\OneDrive\\Dokumenty\\insurance.csv");
//    }
}
