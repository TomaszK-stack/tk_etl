package com.example.tk_etlproc.processing.steps.filter;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ConditionType {
    EQUAL,
    NOT_EQUAL,
    LARGER,
    SMALLER

}
