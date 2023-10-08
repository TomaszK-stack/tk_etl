package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.reading.InputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Service
public class FileCSVSource implements FileSource {
    private InputHandler inputHandler;
    @Autowired
    public FileCSVSource(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public List<OutputFromStep> read(ConfigFileDTO configFileDTO) throws FileNotFoundException, StepNotFoundException {
        StringBuilder data = get_data_from_file(configFileDTO.getPath());
        return inputHandler.handle_data( data,configFileDTO.getDelimiter(), configFileDTO.header, configFileDTO.getConfigProcessingDTO());

    }

    public StringBuilder get_data_from_file(String path) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append("\n");
            }
        }
        System.out.println(content);

        return content;
    }

}
