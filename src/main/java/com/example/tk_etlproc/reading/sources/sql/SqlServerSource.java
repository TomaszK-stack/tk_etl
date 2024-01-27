package com.example.tk_etlproc.reading.sources.sql;

import com.example.tk_etlproc.api.DTO.source.ConfigDatabaseDTO;
import com.example.tk_etlproc.exceptions.InvalidColumnNameException;
import com.example.tk_etlproc.exceptions.StepNotFoundException;
import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.reading.InputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@Service
public class SqlServerSource implements SqlSource {

    private InputHandler inputHandler;
    @Autowired
    public SqlServerSource(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public List<OutputFromStep> read(ConfigDatabaseDTO configDTO) throws ClassNotFoundException, SQLException, StepNotFoundException, InvalidColumnNameException, IOException {
        String connectionurl="jdbc:sqlserver://" + configDTO.getHost()
                + ":"  + configDTO.getPort()
                + ";DatabaseName=" + configDTO.getDatabaseName()
                + ";user=" + configDTO.getUser()
                + ";password=" + configDTO.getPassword()
                + ";encrypt=true;trustServerCertificate=true;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(connectionurl);
        if (connection != null) {
            System.out.println("Connection created successfully..");
        }
        String sql = "{CALL " + configDTO.getInterface_name() + "()}";
        CallableStatement callableStatement = connection.prepareCall(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = callableStatement.executeQuery();

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        StringBuilder sb = new StringBuilder();
        if(resultSet.next()) {
            resultSet.previous();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) sb.append(",");
                String columnName = rsmd.getColumnName(i);
                sb.append(columnName);
            }
            sb.append("\n");


            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) sb.append(",");

                    String columnValue = resultSet.getString(i);
                    sb.append(columnValue);

                }
                sb.append("\n");
            }
            return inputHandler.handle_data(sb,",", true, configDTO);
        }
        else {
            return null;
        }
    }


}
