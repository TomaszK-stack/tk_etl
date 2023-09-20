package com.example.tk_etlproc.processing.steps.nullif;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class NullifStepMeta {
    private String columnName;
    private String columnNameValueExpression;
    private String valueLogicExpression;

}
