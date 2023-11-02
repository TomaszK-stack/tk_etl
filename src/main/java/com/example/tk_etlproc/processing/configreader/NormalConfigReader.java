package com.example.tk_etlproc.processing.configreader;
import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.processing.steps.groupby.GroupByStep;
import com.example.tk_etlproc.processing.steps.groupby.GroupByStepMeta;
import com.example.tk_etlproc.processing.steps.join.JoinColumnStepMeta;
import com.example.tk_etlproc.processing.steps.join.JoinColumnsStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStepMeta;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NormalConfigReader implements ConfigReader {

    @Override
    public List<BaseStep> readConfig(ConfigProcessingDTO processingDTO) throws StepNotFoundException {
        List<BaseStep> stepList = new ArrayList<>();
        for (Map.Entry<String, HashMap<String, Object>> entry : processingDTO.getConfigProcessmap().entrySet()) {
            BaseStep step = getStep(entry.getKey(), entry.getValue());
            stepList.add(step);
        }
        return stepList;
    }

    private BaseStep getStep(String stepName, HashMap<String, Object> stepMetaList) throws StepNotFoundException {
        switch (stepName.toLowerCase()) {
            case "nullif":
                NullifStep nullifStep = new NullifStep();
                NullifStepMeta nullifStepMeta = NullifStepMeta.builder()
                        .columnName((String) stepMetaList.get("columnName"))
                        .columnNameValueExpression((String) stepMetaList.get("columnValueExpression"))
                        .valueLogicExpression((String) stepMetaList.get("valueLogicExpression"))
                        .build();
                nullifStep.setNullifStepMeta(nullifStepMeta);
                return nullifStep;
            case "joincolumn":
                JoinColumnStepMeta joinColumnStepMeta = JoinColumnStepMeta.builder()
                        .columnsToJoinList((List<String>) stepMetaList.get("columnToJoin"))
                        .newColumnName((String) stepMetaList.get("newColumn"))
                        .build();
                JoinColumnsStep joinColumnsStep = new JoinColumnsStep(joinColumnStepMeta);
                return joinColumnsStep;
            case "groupby":
                GroupByStepMeta groupByStepMeta =  GroupByStepMeta.builder()
                        .columnTogroup((String) stepMetaList.get("columnToGroup"))
                        .OperationColumn((String) stepMetaList.get("operationColumn"))
                        .operation((String) stepMetaList.get("operation"))
                        .build();
                GroupByStep groupByStep = new GroupByStep(groupByStepMeta);
                return groupByStep;
//                cases....
                    default:
                throw new StepNotFoundException("Invalid step name");
        }


    }
}
