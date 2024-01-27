package com.example.tk_etlproc.reading.sources.sql;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SqlSource {
    List<OutputFromStep> read(ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException;


}
