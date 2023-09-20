package com.example.tk_etlproc.processing;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InputStepMeta {
    private int columnNumber;
    private List<String> columnNames;


}
