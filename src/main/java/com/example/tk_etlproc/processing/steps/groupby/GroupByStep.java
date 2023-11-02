package com.example.tk_etlproc.processing.steps.groupby;


import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupByStep extends BaseStep {

    private GroupByStepMeta groupByStepMeta;

    public GroupByStep(InputStepData inputStepData, InputStepMeta inputStepMeta, GroupByStepMeta groupByStepMeta) {
        super(inputStepData, inputStepMeta);
        this.groupByStepMeta = groupByStepMeta;
    }

    @Override
    public OutputFromStep processData()  {
        int groupIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getColumnTogroup());
        int operationIndex = inputStepMeta.getColumnNames().indexOf(groupByStepMeta.getOperationColumn());
        if(groupIndex == -1 || (operationIndex == -1 && groupByStepMeta.getOperationColumn() != null)){
            throw new IllegalArgumentException("Entered invalid column name to group/for operation");
        }
        switch (groupByStepMeta.getOperation()){
            case "sum":
                sum(groupIndex, operationIndex, false);
                break;
            default:
                throw new IllegalArgumentException(groupByStepMeta.getOperation() + " is illegal operation type.");

        }


        return new  OutputFromStep(this.inputStepData, this.inputStepMeta);



    }
    private void sumAvg(int groupIndex, int operationIndex, boolean avg){
        List<List<Object>> newData = new ArrayList<>();
        Map<Object, List<Object>> groupMap = new LinkedHashMap<>();
        for (List<Object> row: inputStepData.getData()){
            if(groupMap.containsKey(row.get(groupIndex))){
                try {
                    List<Object> operationRow = groupMap.get(row.get(groupIndex));
                    var value = operationRow.get(operationIndex);
                    Double oldValue = value instanceof Double ? (Double) value :  Double.valueOf((String) operationRow.get(operationIndex));
                    Double newValue = oldValue + Double.valueOf((String) row.get(operationIndex));
                    operationRow.set(operationIndex, newValue);
                }catch (ClassCastException e){
                    throw new ClassCastException("You Can not sum values of columns of index " + operationIndex);
                }

            } else  {
                groupMap.put(row.get(groupIndex), row);
            }
        }
        newData = groupMap.values().stream()
                .collect(Collectors.toList());
        inputStepData.setData(newData);

    }

}
