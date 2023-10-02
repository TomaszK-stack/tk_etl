package com.example.tk_etlproc.api.DTO;

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
