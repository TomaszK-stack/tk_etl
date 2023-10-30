package com.example.tk_etlproc.processing.steps.groupby;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Getter;

import java.util.List;

@Getter
public class GroupByStepMeta extends BaseStepMeta {
    private String columnTogroup;

    private String operation;

    private String OperationColumn;

}
