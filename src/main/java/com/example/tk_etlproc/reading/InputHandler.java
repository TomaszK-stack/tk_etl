package com.example.tk_etlproc.reading;


import com.example.tk_etlproc.api.DTO.ConfigFileDTO;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStep;
import com.example.tk_etlproc.processing.steps.nullif.NullifStepMeta;
import com.example.tk_etlproc.reading.sources.file.FileSource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.*;

@Component
public class InputHandler {

    private Map<String, FileSource> sourceMap;




    public void handle_data(String delimiter, StringBuilder data){
        InputStepData inputStepData = prepare_data(data, delimiter);
        InputStepMeta inputStepMeta = prepareMeta(inputStepData);

        NullifStepMeta nullifStepMeta = NullifStepMeta.builder()
                .columnName("age")
                .columnNameValueExpression("age")
                .valueLogicExpression("19")
                .build();
        NullifStep nullifStep = new NullifStep(inputStepData, inputStepMeta, nullifStepMeta);
        nullifStep.processData();
        System.out.println(inputStepData.getData());

        for(String[] s: inputStepData.getData()){
            for(String z: s){
                System.out.println(z + ",");
            }
            System.out.println("\n");
        }

    }

    private InputStepData prepare_data(StringBuilder data, String delimiter){
        List<String[]> preparedData = new ArrayList<>();
        String stringData = data.toString();
        String[] splittedData = stringData.split("\n");
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
