package com.example.tk_etlproc.processing.steps.nullif;

import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class NullifStepMeta extends BaseStepMeta {

    private String columnName;
    private String columnNameValueExpression;
    private String valueLogicExpression;

}
