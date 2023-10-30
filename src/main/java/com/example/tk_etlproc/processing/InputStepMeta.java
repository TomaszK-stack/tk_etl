package com.example.tk_etlproc.processing;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InputStepMeta implements Cloneable {
    private int columnNumber;
    private List<String> columnNames;


    @Override
    public InputStepMeta clone() {
        try {
            InputStepMeta clone = (InputStepMeta) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
