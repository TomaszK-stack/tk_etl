package com.example.tk_etlproc.processing.steps;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class BaseStep {
    protected InputStepData inputStepData;
    protected InputStepMeta inputStepMeta;

    public BaseStep() {
    }

    protected BaseStep(InputStepData inputStepData, InputStepMeta inputStepMeta) {
        this.inputStepData = inputStepData;
        this.inputStepMeta = inputStepMeta;
    }
    public OutputFromStep processData()   {
        for (List<Object> row: inputStepData.getData()){
            row = processRow(row);
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);
    }
    protected List<Object> processRow(List<Object> row){
        return null;
    }
    protected void modifyMeta(){}

    protected void prepareData(){}
}
