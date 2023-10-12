package com.example.tk_etlproc.processing.steps;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
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
    public OutputFromStep processData(){
        for (Object[] row: inputStepData.getData()){
            row = processRow(row);
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);
    }
    protected Object[] processRow(Object[] row){
        return null;
    }
}
