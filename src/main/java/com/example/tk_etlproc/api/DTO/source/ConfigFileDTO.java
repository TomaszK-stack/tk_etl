package com.example.tk_etlproc.api.DTO.source;

import lombok.Data;

@Data
public class ConfigFileDTO extends BaseDTO {
    public String path;
    public String delimiter;

    public boolean header;


}
