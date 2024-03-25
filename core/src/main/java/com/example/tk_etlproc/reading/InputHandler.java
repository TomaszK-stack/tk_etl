package com.example.tk_etlproc.reading;


import com.example.tk_etlproc.api.DTO.source.BaseDTO;
import com.example.tk_etlproc.archive.manager.ArchiveManager;
import com.example.tk_etlproc.archive.save.ArchiveSaveType;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.processing.configreader.NormalConfigReader;
import com.example.tk_etlproc.processing.steps.BaseStep;
import com.example.tk_etlproc.saving.BaseDestination;
import com.example.tk_etlproc.saving.DestinationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InputHandler {

    private NormalConfigReader configReader;

    private DestinationReader destinationReader;

    private ArchiveManager archiveManager;

    @Autowired
    public InputHandler(NormalConfigReader configReader, DestinationReader destinationReader, ArchiveManager archiveManager) {
        this.configReader = configReader;
        this.destinationReader = destinationReader;
        this.archiveManager = archiveManager;
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
        archiveManager.saveArchive(prepareArchive(inputStepData, inputStepMeta, delimiter), ArchiveSaveType.OUT);
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
    private StringBuilder prepareArchive(InputStepData data, InputStepMeta meta,  String delimiter){
        StringBuilder archiveData = new StringBuilder();
        archiveData.append(meta.getColumnNames().toString()
                .replace(",",delimiter)
                .replace("[", "")
                .replace("]", "") + "\n");

        data.getData().forEach(row->archiveData.append(
                row.toString()
                        .replace(",",delimiter)
                        .replace("[", "")
                        .replace("]", "") + "\n"
        ));
        return archiveData;
    }


}
