package com.example.tk_etlproc.reading;


import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.configreader.NormalConfigReader;
import com.example.tk_etlproc.processing.steps.BaseStep;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InputHandler {

    private NormalConfigReader configReader;

    public InputHandler(NormalConfigReader configReader) {
        this.configReader = configReader;
    }


    public void handle_data(StringBuilder data, String delimiter, boolean header, ConfigProcessingDTO processingDTO) throws StepNotFoundException {
        InputStepData inputStepData = prepare_data(data, delimiter, header);
        InputStepMeta inputStepMeta = prepareMeta(inputStepData);
        List<BaseStep> stepList =  configReader.readConfig(processingDTO);
        for(BaseStep step: stepList){

            step.setInputStepMeta(inputStepMeta);
            step.setInputStepData(inputStepData);
            step.processData();
        }



    }

    private InputStepData prepare_data(StringBuilder data, String delimiter, boolean header){
        List<String[]> preparedData = new ArrayList<>();
        String stringData = data.toString();
        String[] splittedData = stringData.split("\n");
        if(!header){
            int columnNumber = splittedData[0].split(delimiter).length;
            String[] headerRow = new String[columnNumber];
            for(int i=0; i<columnNumber; i++ ){
                headerRow[i] = "col" + i;
            }
            preparedData.add(headerRow);
        }
        for(String s: splittedData){
            String[] row = s.split(delimiter);
            preparedData.add(row);
        }
        return InputStepData.builder()
                .data(preparedData)
                .build();
    }
    private InputStepMeta prepareMeta(InputStepData inputStepData){
        String[] columns = inputStepData.getData().get(0);
        List<String> columnsList = Arrays.stream(columns).toList();
        int columnsNumber = columnsList.size();
        return InputStepMeta.builder()
                .columnNumber(columnsNumber)
                .columnNames(columnsList)
                .build();
    }



}
