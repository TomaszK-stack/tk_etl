package com.example.tk_etlproc.processing.steps.join;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Data;

import java.util.List;

@Data
public class JoinColumnStepMeta extends BaseStepMeta {
    private List<String> columnsToJoinList;
    private String newColumnName;
}
