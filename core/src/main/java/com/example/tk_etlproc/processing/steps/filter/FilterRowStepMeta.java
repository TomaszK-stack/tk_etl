package com.example.tk_etlproc.processing.steps.filter;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterRowStepMeta extends BaseStepMeta {
    public ConditionType conditionType;
    public String column1;
    public String column2;

    public boolean meetsCondition;

    public boolean staticValue;


}
