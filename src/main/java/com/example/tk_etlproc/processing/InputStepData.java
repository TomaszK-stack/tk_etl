package com.example.tk_etlproc.processing;


import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@Builder
@Data
public class InputStepData implements Cloneable {

    public List<List<Object>> data;

    public InputStepData(List<List<Object>> data) {
        this.data = data;
    }

    @Override
    public InputStepData clone() {
        try {
            InputStepData clone = (InputStepData) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
