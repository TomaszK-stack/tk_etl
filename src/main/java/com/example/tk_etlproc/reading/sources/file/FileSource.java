package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.ConfigFileDTO;

import java.io.FileNotFoundException;

public interface FileSource {
    void read(ConfigFileDTO configFileDTO) throws FileNotFoundException;}
