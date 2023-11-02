package com.example.tk_etlproc.processing;


import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Builder
@Data
public class InputStepData  {

    public List<List<Object>> data;

    public InputStepData(List<List<Object>> data) {
        this.data = data;
    }

    public InputStepData(InputStepData inputStepData) {
        List<List<Object>> newData = new ArrayList<>();

        for (List<Object> row: inputStepData.getData()){
            newData.add(new ArrayList<>(row));
        }
        this.data = newData;
    }


}
