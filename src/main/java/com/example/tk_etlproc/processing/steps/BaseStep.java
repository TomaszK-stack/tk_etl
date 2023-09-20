package com.example.tk_etlproc.processing.steps;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;

public abstract class BaseStep {
    protected InputStepData inputStepData;
    protected InputStepMeta inputStepMeta;

    protected BaseStep(InputStepData inputStepData, InputStepMeta inputStepMeta) {
        this.inputStepData = inputStepData;
        this.inputStepMeta = inputStepMeta;
    }
    protected void processData(){}

}
