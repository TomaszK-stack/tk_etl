package com.example.tk_etlproc.processing.steps.groupby;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class GroupByStepMeta extends BaseStepMeta {
    private String columnTogroup;

    private String operation;

    private String OperationColumn;

}
