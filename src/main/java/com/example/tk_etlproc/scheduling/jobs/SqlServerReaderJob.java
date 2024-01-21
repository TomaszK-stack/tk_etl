package com.example.tk_etlproc.scheduling.jobs;



import com.example.tk_etlproc.api.DTO.schedule.SqlScheduleDTO;
import com.example.tk_etlproc.reading.sources.sql.SqlServerSource;
import com.example.tk_etlproc.scheduling.JobDataStore;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlServerReaderJob implements Job {

    private SqlServerSource sqlSource;

    @Autowired
    public SqlServerReaderJob(SqlServerSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        SqlScheduleDTO config = (SqlScheduleDTO) context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.DATA_STORE));
        sqlSource.read(config.getConfigDatabaseDTO());
    }
}
