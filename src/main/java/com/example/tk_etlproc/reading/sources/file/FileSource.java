package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;

import java.io.IOException;

public interface FileSource {
    void read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException;}
