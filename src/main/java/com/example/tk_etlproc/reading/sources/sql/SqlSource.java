package com.example.tk_etlproc.reading.sources.sql;

import com.example.tk_etlproc.api.DTO.ConfigDatabaseDTO;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface SqlSource {
    void read(ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException;


}
