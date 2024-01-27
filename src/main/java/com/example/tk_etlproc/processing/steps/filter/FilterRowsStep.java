package com.example.tk_etlproc.processing.steps.filter;

import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class FilterRowsStep extends BaseStep {
    private FilterRowStepMeta filterRowStepMeta;

    public FilterRowsStep(InputStepData inputStepData, InputStepMeta inputStepMeta, FilterRowStepMeta filterRowStepMeta) {
        super(inputStepData, inputStepMeta);
        this.filterRowStepMeta = filterRowStepMeta;
    }

    @SneakyThrows
    @Override
    public OutputFromStep processData()  {
        List<List<Object>> newData = new ArrayList<>();
        int index1 = inputStepMeta.getColumnNames().indexOf(filterRowStepMeta.getColumn1());
        int index2 = inputStepMeta.getColumnNames().indexOf(filterRowStepMeta.getColumn2());
        if (index1 == -1 || index2 == -1 ){
            throw new InvalidColumnNameException("You entered column name which not exists in previous step!!!");
        }

        for (List<Object> row: inputStepData.getData()){
            boolean expectConditionRow = evaluateExpressionForRow(row, index1, index2);
            if (filterRowStepMeta.isMeetsCondtion()){
                if (expectConditionRow){
                    newData.add(row);
                }

            }else {
                if (!expectConditionRow){
                    newData.add(row);
                }
            }

        }

        return new OutputFromStep(this.inputStepData, this.inputStepMeta);
    }
    private boolean evaluateExpressionForRow(List<Object> row, int index1, int index2){


    }


}
