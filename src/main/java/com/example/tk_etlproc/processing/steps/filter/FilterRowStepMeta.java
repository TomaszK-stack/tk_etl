package com.example.tk_etlproc.processing.steps.filter;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Data;

@Data
public class FilterRowStepMeta extends BaseStepMeta {
    public ConditionType conditionType;
    public String column1;
    public String column2;

    public boolean meetsCondtion;


}
