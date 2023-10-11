package com.example.tk_etlproc.api.DTO.source;

import com.example.tk_etlproc.api.DTO.destination.BaseDestinationEndpoint;
import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import lombok.Getter;

import java.util.List;


@Getter
public abstract class BaseDTO {
    public ConfigProcessingDTO configProcessingDTO;
    public BaseDestinationEndpoint baseDestinationEndpoint;

    public String destinationType;
    public List<String> destinationElementsList;


}
