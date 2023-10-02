package com.example.tk_etlproc.reading.sources.sql;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;

import java.sql.SQLException;

public interface SqlSource {
    void read(ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException, StepNotFoundException;


}
