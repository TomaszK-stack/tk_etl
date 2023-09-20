package com.example.tk_etlproc.processing;


import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class InputStepData {

    public List<String[]> data;

    public InputStepData(List<String[]> data) {
        this.data = data;
    }
}
