package com.example.tk_etlproc.processing.steps.groupby;

import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStepMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GroupByStep extends BaseStep {

    private GroupByStepMeta groupByStepMeta;

    public GroupByStep(InputStepData inputStepData, InputStepMeta inputStepMeta, GroupByStepMeta groupByStepMeta) {
        super(inputStepData, inputStepMeta);
        this.groupByStepMeta = groupByStepMeta;
    }

    @Override
    public OutputFromStep processData() throws InvalidColumnNameException {
        int groupIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getColumnTogroup());
        int operationIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getOperationColumn());
        if(groupIndex == -1 || (operationIndex == -1 && groupByStepMeta.getOperationColumn() != null)){
            throw new InvalidColumnNameException("Entered invalid column name to group/for operation");
        }
        Map<Object, List<Object>> groupMap = new HashMap<>();
        for (List<Object> row: inputStepData.getData()){
            if(groupMap.containsKey(row.get(groupIndex))){

            }
        }
    return null;
    }
}
