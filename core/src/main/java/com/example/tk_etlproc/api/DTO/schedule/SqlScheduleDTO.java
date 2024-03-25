package com.example.tk_etlproc.api.DTO.schedule;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import lombok.Data;

@Data
public class SqlScheduleDTO extends BaseScheduleDTO{
    public ConfigDatabaseDTO configDatabaseDTO;

}
