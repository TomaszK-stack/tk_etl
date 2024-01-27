package com.example.tk_etlproc.reading;


import com.example.tk_etlproc.api.DTO.source.BaseDTO;
import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.exceptions.InvalidOperationNameException;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.configreader.NormalConfigReader;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.saving.BaseDestination;
import com.example.tk_etlproc.saving.DestinationReader;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.io.IOException;
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


    public List<OutputFromStep> handle_data(StringBuilder data, String delimiter, boolean header, BaseDTO baseDTO) throws StepNotFoundException, SQLException, ClassNotFoundException, IOException {
        InputStepData inputStepData = prepare_data(data, delimiter, header);
        InputStepMeta inputStepMeta = prepareMeta(inputStepData, header);
        List<BaseStep> stepList = configReader.readConfig(baseDTO.getConfigProcessingDTO());
        List<OutputFromStep> outputList = new ArrayList<>();
        for (BaseStep step : stepList) {
            step.setInputStepMeta(new InputStepMeta(inputStepMeta));
            step.setInputStepData(new InputStepData(inputStepData));
            outputList.add(step.processData());
            inputStepData = step.getInputStepData();
            inputStepMeta = step.getInputStepMeta();
        }
        if(baseDTO.getDestinationType() != null) {
            BaseDestination destination = destinationReader.readDestinationConfig(baseDTO.getDestinationType(), baseDTO.getDestinationElementsList());
            destination.save(inputStepData, inputStepMeta, header);
        }

        return outputList;


    }

    private InputStepData prepare_data(StringBuilder data, String delimiter, boolean header) {
        List<List<Object>> preparedData = new ArrayList<>();
        String stringData = data.toString();
        String[] splittedData = stringData.split("\n");
        int i = 0;
        for (String s : splittedData) {
            List<String> stringRow = Arrays.stream(s.split(delimiter)).toList();
            List<Object> row = new ArrayList<>();
            stringRow.forEach(r -> row.add(r));
            preparedData.add(row);
            i++;
        }

        return InputStepData.builder()
                .data(preparedData)
                .build();
    }

    private InputStepMeta prepareMeta(InputStepData inputStepData, boolean header) {
        List<String> columnList = new ArrayList<>();
        if (header) {
            List<Object> columnListObject = inputStepData.getData().get(0);
            columnListObject.forEach(x -> columnList.add((String) x));
        } else {
            for (int i = 1; i <= inputStepData.getData().get(0).size(); i++) {
                columnList.add("col" + i);

            }

        }
        inputStepData.getData().remove(0);
        return InputStepMeta.builder()
                .columnNumber(columnList.size())
                .columnNames(columnList)
                .build();

    }


}
