package com.example.tk_etlproc.scheduling.jobs;



import com.example.tk_etlproc.api.DTO.schedule.SqlScheduleDTO;
import com.example.tk_etlproc.reading.sources.sql.SqlSourceDefault;
import com.example.tk_etlproc.reading.sources.sql.SqlType;
import com.example.tk_etlproc.scheduling.JobDataStore;
import lombok.SneakyThrows;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@Component
public class SqlServerReaderJob implements Job {

    private SqlSourceDefault sqlSource;

    @Autowired
    public SqlServerReaderJob(SqlSourceDefault sqlSource) {
        this.sqlSource = sqlSource;
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SqlScheduleDTO config = (SqlScheduleDTO) context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.DATA_STORE));
        sqlSource.read(config.getConfigDatabaseDTO(), SqlType.SQL_SERVER);
    }
}
