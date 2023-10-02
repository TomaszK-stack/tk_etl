package com.example.tk_etlproc.api.DTO.source;

import com.example.tk_etlproc.api.DTO.processing.ConfigProcessingDTO;
import lombok.Data;

@Data
public class ConfigDatabaseDTO extends BaseDTO {
    public String host;
    public int port;
    public String databaseName;

    public String user;

    public String password;

    public String interface_name;
    public ConfigProcessingDTO configProcessingDTO;




}
