package com.example.tk_etlproc.api.DTO.schedule;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseScheduleDTO implements Serializable {
    public int timeout;
    public String sourceName;
}
