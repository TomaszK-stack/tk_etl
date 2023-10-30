package com.example.tk_etlproc.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

@Data
@NoArgsConstructor
public class TestDTO {
    public boolean test;


    public Test2Dto t;
    public TestDTO(boolean test, Test2Dto t) {
        this.test = test;
        this.t = t;
    }


    public boolean isTest() {
        return test;
    }

}
