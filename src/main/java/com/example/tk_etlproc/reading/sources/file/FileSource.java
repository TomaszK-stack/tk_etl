package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FileSource {
   List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException, SQLException, ClassNotFoundException;}
