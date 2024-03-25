package com.example.tk_etlproc.saving;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;

public abstract class BaseDestination {

    @SneakyThrows
    public void save(InputStepData inputStepData, InputStepMeta inputStepMeta, boolean header) throws ClassNotFoundException, SQLException, IOException {}
}
