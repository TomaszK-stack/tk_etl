package com.example.tk_etlproc.api.DTO;

import lombok.Data;

@Data
public class ConfigDatabaseDTO {
    public String host;
    public int port;
    public String databaseName;

    public String user;

    public String password;

    public String interface_name;



}
