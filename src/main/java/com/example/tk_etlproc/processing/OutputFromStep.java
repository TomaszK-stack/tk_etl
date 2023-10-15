package com.example.tk_etlproc.processing;
import lombok.Data;

@Data
public class OutputFromStep {
    private InputStepData inputStepData;
    private InputStepMeta inputStepMeta;

    public OutputFromStep(InputStepData inputStepData, InputStepMeta inputStepMeta) {
        this.inputStepData = inputStepData;
        this.inputStepMeta = inputStepMeta;
    }

    @Override
    public String toString() {
        return "OutputFromStep{" +
                "inputStepData=" + inputStepData.data +
                ", inputStepMeta=" + inputStepMeta +
                '}';
    }
}
