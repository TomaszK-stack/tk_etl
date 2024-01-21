package com.example.tk_etlproc.api.DTO.schedule;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import lombok.Data;

@Data
public class FileScheduleDTO extends BaseScheduleDTO{
    public ConfigFileDTO configFileDTO;
    public String sentdir;
}
