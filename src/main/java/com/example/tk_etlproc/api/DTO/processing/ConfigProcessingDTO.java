package com.example.tk_etlproc.api.DTO.processing;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class ConfigProcessingDTO implements Serializable {
    public LinkedHashMap<String, HashMap<String, Object>> configProcessmap;

}
