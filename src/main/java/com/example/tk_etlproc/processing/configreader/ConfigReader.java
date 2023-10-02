package com.example.tk_etlproc.processing.configreader;

import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.steps.BaseStep;

import java.util.List;

public interface ConfigReader {
    List<BaseStep> readConfig(ConfigProcessingDTO processingDTO) throws StepNotFoundException;
}
