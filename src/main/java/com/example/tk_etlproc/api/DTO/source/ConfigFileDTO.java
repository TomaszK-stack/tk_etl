package com.example.tk_etlproc.api.DTO.source;

import lombok.Data;
import lombok.Getter;

@Data
public class ConfigFileDTO extends BaseDTO {
    public String path;
    public String delimiter;

    public boolean header;



}
