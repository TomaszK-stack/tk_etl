package com.example.tk_etlproc.reading;



import com.example.tk_etlproc.api.DTO.source.BaseDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.configreader.NormalConfigReader;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.saving.BaseDestination;
import com.example.tk_etlproc.saving.DestinationReader;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;

@Component
public class InputHandler {

    private NormalConfigReader configReader;

    private DestinationReader destinationReader;

    public InputHandler(NormalConfigReader configReader, DestinationReader destinationReader) {
        this.configReader = configReader;
        this.destinationReader = destinationReader;
    }


    public List<OutputFromStep> handle_data(StringBuilder data, String delimiter, boolean header, BaseDTO baseDTO) throws StepNotFoundException, SQLException, ClassNotFoundException {
        InputStepData inputStepData = prepare_data(data, delimiter, header);
        InputStepMeta inputStepMeta = prepareMeta(inputStepData);
        List<BaseStep> stepList =  configReader.readConfig(baseDTO.getConfigProcessingDTO());
        List<OutputFromStep> outputList = new ArrayList<>();
        for(BaseStep step: stepList){
            step.setInputStepMeta(inputStepMeta);
            step.setInputStepData(inputStepData);
            outputList.add(step.processData());
        }
        BaseDestination destination = destinationReader.readDestinationConfig(baseDTO.getDestinationType(),  baseDTO.getDestinationElementsList());
        destination.save(inputStepData, inputStepMeta, header);
        return outputList;


    }

    private InputStepData prepare_data(StringBuilder data, String delimiter, boolean header){
        List<Object[]> preparedData = new ArrayList<>();
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
        String[] columns = (String[]) inputStepData.getData().get(0);
        List<String> columnsList = Arrays.stream(columns).toList();
        int columnsNumber = columnsList.size();
        return InputStepMeta.builder()
                .columnNumber(columnsNumber)
                .columnNames(columnsList)
                .build();
    }



}
