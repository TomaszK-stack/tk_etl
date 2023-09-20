package com.example.tk_etlproc.processing.steps.nullif;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.steps.BaseStep;

public class NullifStep extends BaseStep {
    private NullifStepMeta nullifStepMeta;
    public NullifStep(InputStepData inputStepData, InputStepMeta inputStepMeta, NullifStepMeta nullifStepMeta) {
        super(inputStepData, inputStepMeta);
        this.nullifStepMeta = nullifStepMeta;
    }

    @Override
    public void processData() {
        String[] headerRow = this.inputStepData.getData().get(0);
        int expressionColumnIndex = 0;
        int columnToChangeIndex = 0;

        for (int i=0; i<=headerRow.length-1;i++){
            if(headerRow[i].equals(nullifStepMeta.getColumnName())) columnToChangeIndex = i;
            if(headerRow[i].equals(nullifStepMeta.getColumnNameValueExpression())) expressionColumnIndex = i;

        }
        for(String[] s: inputStepData.getData()){
            if(s[expressionColumnIndex].equals(nullifStepMeta.getValueLogicExpression())) s[columnToChangeIndex] = null;
        }

    }
}
