package com.example.tk_etlproc.processing.steps.join;
import com.example.tk_etlproc.processing.InputStepMeta;
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

    public JoinColumnsStep(JoinColumnStepMeta joinStepMeta) {
        this.joinStepMeta = joinStepMeta;
        this.columntToJoinIndexes = new ArrayList<>();
        prepareData();

    }

    @Override
    protected List<Object> processRow(List<Object> row) {
        StringBuilder valueToAdd = new StringBuilder();

        for (Integer i : columntToJoinIndexes) {
            valueToAdd.append(row.get(i));
            row.remove(i);
        }
        row.add(valueToAdd.toString());
        return row;

    }

    private void prepareData() {
        for (String column : joinStepMeta.getColumnsToJoinList()) {
            columntToJoinIndexes.add(this.inputStepMeta.getColumnNames().indexOf(column));
        }
    }

    @Override
    protected void modifyMeta() {
        this.columntToJoinIndexes.forEach(i -> this.inputStepMeta.getColumnNames().remove(i));
    }

    @Override
    public void setInputStepMeta(InputStepMeta inputStepMeta) {
        super.setInputStepMeta(inputStepMeta);
        modifyMeta();
    }
}
