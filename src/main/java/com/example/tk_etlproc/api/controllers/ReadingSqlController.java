package com.example.tk_etlproc.api.controllers;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.reading.sources.sql.SqlServerSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("config/sql")
public class ReadingSqlController {

     public SqlServerSource sqlServerSource;

    public ReadingSqlController(SqlServerSource sqlServerSource) {
        this.sqlServerSource = sqlServerSource;
    }

    @PostMapping("/sqlserver")
    public ResponseEntity<String> sqlServerGetData(@RequestBody ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException, StepNotFoundException {
        System.out.println(configDTO);
        sqlServerSource.read(configDTO);
        return ResponseEntity.ok("");
    }
}
