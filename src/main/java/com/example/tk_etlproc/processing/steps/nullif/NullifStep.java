package com.example.tk_etlproc.processing.steps.nullif;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.Data;
import lombok.Setter;


@Data
@Setter
public class NullifStep extends BaseStep {
    private NullifStepMeta nullifStepMeta;

    public NullifStep() {
        super();
    }

    public NullifStep(InputStepData inputStepData, InputStepMeta inputStepMeta, NullifStepMeta nullifStepMeta) {
        super(inputStepData, inputStepMeta);
        this.nullifStepMeta = nullifStepMeta;
    }

    @Override
    public OutputFromStep processData() {
        String[] headerRow = (String[]) this.inputStepData.getData().get(0);
        int expressionColumnIndex = 0;
        int columnToChangeIndex = 0;

        for (int i=0; i<=headerRow.length-1;i++){
            if(headerRow[i].strip().equals(nullifStepMeta.getColumnName())) columnToChangeIndex = i;
            if(headerRow[i].strip().equals(nullifStepMeta.getColumnNameValueExpression())) expressionColumnIndex = i;

        }
        for(Object[] s: inputStepData.getData()){
            if(s[expressionColumnIndex].equals(nullifStepMeta.getValueLogicExpression())) s[columnToChangeIndex] = null;
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);

    }
}
