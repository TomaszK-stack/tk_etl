package com.example.tk_etlproc.processing;


import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@Builder
@Data
public class InputStepData {

    public List<String[]> data;

    public InputStepData(List<String[]> data) {
        this.data = data;
    }

    public void printData(){
        for (String[] row: data){
            Arrays.stream(row).forEach(System.out::print);
        }
    }


}
