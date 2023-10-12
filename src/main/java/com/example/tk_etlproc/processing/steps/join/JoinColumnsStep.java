package com.example.tk_etlproc.processing.steps.join;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class JoinColumnsStep extends BaseStep {
    private JoinColumnStepMeta joinStepMeta;
    private List<Integer> columntToJoinIndexes;
    public JoinColumnsStep(InputStepData inputStepData, InputStepMeta inputStepMeta, JoinColumnStepMeta joinStepMeta) {
        super(inputStepData, inputStepMeta);
        this.columntToJoinIndexes = new ArrayList<>();
        this.joinStepMeta = joinStepMeta;
    }

//    @Override
//    protected Object[] processRow(Object[] row) {
//        List<Object> list = new ArrayList<>();
//        for(Integer i: columntToJoinIndexes){
//            list.add(row[i]);
//
//        }
//        list.st
//
//    }
    private void prepareData(){
        for(String column: joinStepMeta.getColumnsToJoinList()){
            columntToJoinIndexes.add(this.inputStepMeta.getColumnNames().indexOf(column));
        }
    }
}
