package com.example.tk_etlproc.reading.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FileService implements MainService {


    @Override
    public String process_data(String data) {
        return data;
    }
}
