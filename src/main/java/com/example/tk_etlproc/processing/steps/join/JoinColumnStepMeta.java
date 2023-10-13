package com.example.tk_etlproc.processing.steps.join;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JoinColumnStepMeta extends BaseStepMeta {
    private List<String> columnsToJoinList;
    private String newColumnName;
}
