package com.example.tk_etlproc.reading;

import com.example.tk_etlproc.api.DTO.ConfigDTO;
import com.example.tk_etlproc.api.DTO.ConfigFileDTO;
import com.example.tk_etlproc.reading.sources.Source;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InputHandler {

    private Map<String, Source> sourceMap;
    private Source FileSource;


    public InputHandler(Source fileSource) {
        FileSource = fileSource;
        this.sourceMap = new HashMap<>();
        this.sourceMap.put("File", fileSource);

    }
    public String handle_data(ConfigFileDTO dto) throws FileNotFoundException {
        return sourceMap.get(dto.getInputType()).read(dto.getPath());


    }



}
