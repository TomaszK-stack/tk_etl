package com.example.tk_etlproc.processing.steps.nullif;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.Data;
import lombok.Setter;

import java.util.List;


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
        List<Object> headerRow =  this.inputStepData.getData().get(0);
        int expressionColumnIndex = 0;
        int columnToChangeIndex = 0;

        columnToChangeIndex = headerRow.indexOf(nullifStepMeta.getColumnName());
        expressionColumnIndex = headerRow.indexOf(nullifStepMeta.getColumnName());


        for(List<Object> s: inputStepData.getData()){
            if(s.get(expressionColumnIndex).equals(nullifStepMeta.getValueLogicExpression())) s.set(columnToChangeIndex, null);
        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);

    }
}
