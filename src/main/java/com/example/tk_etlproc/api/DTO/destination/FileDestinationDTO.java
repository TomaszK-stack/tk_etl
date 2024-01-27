package com.example.tk_etlproc.api.DTO.destination;

import lombok.Data;

@Data
public class FileDestinationDTO implements BaseDestinationEndpoint{
    public String path;
    public String delimiter;
}
