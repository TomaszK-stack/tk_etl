package com.example.tk_etlproc.reading.sources.file;

import com.example.tk_etlproc.api.DTO.source.ConfigFileDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.reading.InputHandler;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileXLSXSource implements FileSource{
    private InputHandler inputHandler;

    public FileXLSXSource(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void read(ConfigFileDTO configFileDTO) throws IOException, StepNotFoundException {
        StringBuilder data = get_data_from_file(configFileDTO.getPath());
        inputHandler.handle_data(data,",", configFileDTO.header,configFileDTO.getConfigProcessingDTO());
    }
    private StringBuilder get_data_from_file(String path){
        StringBuilder data = new StringBuilder();
        try {
            Workbook workbook = WorkbookFactory.create(new File(path));

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            data.append(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            data.append(cell.getBooleanCellValue());
                            break;
                        default:
                            break;
                    }
                    data.append(",");
                }
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    }



