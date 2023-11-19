package com.example.tk_etlproc.saving.sql;

import com.example.tk_etlproc.processing.InputStepData;
import com.example.tk_etlproc.processing.InputStepMeta;
import com.example.tk_etlproc.saving.BaseDestination;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
public class SqlServerDestination extends BaseDestination {

    public String host;
    public String port;
    public String databaseName;

    public String user;

    public String password;

    public String tableName;

    @Override
    public void save(InputStepData inputStepData, InputStepMeta inputStepMeta, boolean header ) throws ClassNotFoundException, SQLException {
        String Connectionurl="jdbc:sqlserver://" + host
                + ":"  + port
                + ";DatabaseName=" + databaseName
                + ";user=" + user
                + ";password=" + password;


        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(Connectionurl);
        String sql = "insert into " + tableName + "(";


        String sql2 =  ")values(";
        for (int i=0; i<inputStepMeta.getColumnNumber();i++){
            if(i!= inputStepMeta.getColumnNumber()-1){
                sql += inputStepMeta.getColumnNames().get(i) + ",";
                sql2+="?,";

            }else {
                sql += inputStepMeta.getColumnNames().get(i);
                sql2+="?";
            }

        }

        sql=sql + sql2 + ")";
        PreparedStatement statement = connection.prepareStatement(sql);

        int row_number = 1;
        int cell_number = 1;
        for(List<Object> row : inputStepData.getData()){
            if(row_number==1) {
                if(!header) {
                    for (Object cell : row) {
                        statement.setObject(cell_number,cell);
                        cell_number++;
                    }
                    statement.executeUpdate();
                    cell_number = 1;
                }

            }else{
                for (Object cell : row) {
                    statement.setObject(cell_number,cell);
                    cell_number++;

                }
                statement.executeUpdate();
                cell_number = 1;
            }
            row_number++;
        }

    }
}
