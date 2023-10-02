package com.example.tk_etlproc.processing.configreader;

import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.processing.steps.BaseStepMeta;
import com.example.tk_etlproc.processing.steps.config.StepsConfiguration;
import com.example.tk_etlproc.processing.steps.nullif.NullifStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStepMeta;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NormalConfigReader implements ConfigReader{
    private ApplicationContext applicationContext;

    public NormalConfigReader() {
        applicationContext = new AnnotationConfigApplicationContext(StepsConfiguration.class);
    }

    @Override
    public List<BaseStep> readConfig(ConfigProcessingDTO processingDTO) throws StepNotFoundException {
        List<BaseStep> stepList = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : processingDTO.getConfigProcessmap().entrySet()){
            BaseStep step = getStep(entry.getKey(), entry.getValue());
            stepList.add(step);
        }
        return stepList;
    }

    private BaseStep getStep(String stepName, List<String> stepMetaList) throws StepNotFoundException {
        switch (stepName.toLowerCase()){
            case "nullif":
                NullifStep nullifStep = applicationContext.getBean(NullifStep.class);
                NullifStepMeta nullifStepMeta = NullifStepMeta
                        .builder()
                        .columnName(stepMetaList.get(0))
                        .columnNameValueExpression(stepMetaList.get(1))
                        .valueLogicExpression(stepMetaList.get(2))
                        .build();
                nullifStep.setNullifStepMeta(nullifStepMeta);
                return nullifStep;


//                cases....
            default:
                throw new StepNotFoundException("Invalid step name");
        }



    }
}
