package com.example.tk_etlproc.processing.steps.join;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.steps.BaseStep;
import lombok.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class JoinColumnsStep extends BaseStep {
    private JoinColumnStepMeta joinStepMeta;
    private List<Integer> columntToJoinIndexes;
    private List<Integer> columntToRemoveIndexes;

    public JoinColumnsStep(JoinColumnStepMeta joinStepMeta) {
        this.joinStepMeta = joinStepMeta;
        columntToJoinIndexes = new ArrayList<>();
        columntToRemoveIndexes = new ArrayList<>();
    }


    @Override
    protected List<Object> processRow(List<Object> row) {
        StringBuilder valueToAdd = new StringBuilder();
        Iterator<Integer> iterator = columntToJoinIndexes.iterator();
        Iterator<Integer> iterator2 = columntToRemoveIndexes.iterator();
        while (iterator.hasNext() && iterator2.hasNext()){
            int i = iterator.next();
            if(row.get(i) !=null) {
                valueToAdd.append(row.get(i));
            }
            row.remove(iterator2.next().intValue());

        }
        row.add(row.size(), valueToAdd.toString());
        return row;

    }

    protected void prepareData() {
        for (String column : joinStepMeta.getColumnsToJoinList()) {
            columntToJoinIndexes.add(inputStepMeta.getColumnNames().indexOf(column));
        }
    }

    @Override
    protected void modifyMeta() {
//        columntToJoinIndexes.forEach(i -> inputStepMeta.getColumnNames().remove(i.intValue()));
        for (String column: joinStepMeta.getColumnsToJoinList()){
            int i = inputStepMeta.getColumnNames().indexOf(column);
            inputStepMeta.getColumnNames().remove(i);
            columntToRemoveIndexes.add(i);
        }
        inputStepMeta.getColumnNames().add(joinStepMeta.getNewColumnName());
        inputStepMeta.setColumnNumber(inputStepMeta.getColumnNames().size());
    }

    @Override
    public void setInputStepMeta(InputStepMeta inputStepMeta) {
        super.setInputStepMeta(inputStepMeta);
        prepareData();
        modifyMeta();


    }
}
