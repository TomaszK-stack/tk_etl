package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.ConfigFileDTO;
import com.example.tk_etlproc.reading.InputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class FileCSVSource implements FileSource {
    private InputHandler inputHandler;
    @Autowired
    public FileCSVSource(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }


    @Override
    public void read(ConfigFileDTO configFileDTO) throws FileNotFoundException {
        StringBuilder data = get_data_from_file(configFileDTO.getPath());
        inputHandler.handle_data(configFileDTO.getDelimiter(), data);
    }

    private StringBuilder get_data_from_file(String path) throws FileNotFoundException {
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