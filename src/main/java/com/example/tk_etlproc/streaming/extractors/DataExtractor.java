package com.example.tk_etlproc.streaming.extractors;

import com.example.tk_etlproc.api.DTO.source.BaseDTO;

import java.util.List;

public interface DataExtractor {

    BaseDTO extractDTO(List data);
}
