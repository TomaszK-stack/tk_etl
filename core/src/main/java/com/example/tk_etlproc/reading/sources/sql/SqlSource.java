package com.example.tk_etlproc.reading.sources.sql;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.reading.sources.Source;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class SqlSource extends Source {

    public abstract List<OutputFromStep> read(ConfigDatabaseDTO configDTO, SqlType type) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException;
    {

    }
}
