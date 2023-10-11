package com.example.tk_etlproc.saving;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;

import java.sql.SQLException;

public abstract class BaseDestination {
    public void save(InputStepData inputStepData, InputStepMeta inputStepMeta, boolean header) throws ClassNotFoundException, SQLException {}
}
