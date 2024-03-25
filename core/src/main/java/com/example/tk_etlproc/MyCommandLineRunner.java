package com.example.tk_etlproc;

import com.example.tk_etlproc.exceptions.StepNotFoundException;
import org.springframework.boot.CommandLineRunner;

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
