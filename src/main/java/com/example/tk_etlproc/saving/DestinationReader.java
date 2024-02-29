package com.example.tk_etlproc.saving;

import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.saving.file.FileDestination;
import com.example.tk_etlproc.saving.sql.SqlServerDestination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationReader {
    public  BaseDestination readDestinationConfig(String destinationType, List<String> destinationElements) throws StepNotFoundException {
        switch (destinationType) {
            case "sqlserver":

                return new SqlServerDestination(destinationElements.get(0), destinationElements.get(1), destinationElements.get(2), destinationElements.get(3), destinationElements.get(4), destinationElements.get(5));

            case "file":
                return new FileDestination(destinationElements.get(0), destinationElements.get(1));
            default:
                throw new StepNotFoundException("Invalid step name");


        }
    }
}
