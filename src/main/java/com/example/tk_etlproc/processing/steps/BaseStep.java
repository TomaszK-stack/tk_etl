package com.example.tk_etlproc.processing.steps;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import lombok.Setter;

@Setter
public abstract class BaseStep {
    protected InputStepData inputStepData;
    protected InputStepMeta inputStepMeta;

    public BaseStep() {
    }

    protected BaseStep(InputStepData inputStepData, InputStepMeta inputStepMeta) {
        this.inputStepData = inputStepData;
        this.inputStepMeta = inputStepMeta;
    }
    public void processData(){}

}
