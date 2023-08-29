package com.example.tk_etlproc.reading.sources;

import com.example.tk_etlproc.reading.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class FileSource implements Source {
    private FileService fileService;
    @Autowired
    public FileSource(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public String read(String path) throws FileNotFoundException {
        String data = get_data_from_file(path);
       return fileService.process_data(data);


    }

    private String get_data_from_file(String path) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append("\n");
            }
        }

        return content.toString();
    }

}
