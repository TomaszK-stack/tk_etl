package com.example.tk_etlproc.scheduling.jobs;


import com.example.tk_etlproc.api.DTO.schedule.FileScheduleDTO;
import com.example.tk_etlproc.reading.sources.file.FileCSVSource;
import com.example.tk_etlproc.reading.sources.file.FileXLSXSource;
import com.example.tk_etlproc.scheduling.JobDataStore;
import lombok.SneakyThrows;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@DisallowConcurrentExecution
public class FileReaderJob implements Job {

    private FileCSVSource fileCSVSource;
    private FileXLSXSource fileXLSXSource;

    @Autowired
    public FileReaderJob(FileCSVSource fileCSVSource, FileXLSXSource fileXLSXSource) {
        this.fileCSVSource = fileCSVSource;
        this.fileXLSXSource = fileXLSXSource;
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       var type =  context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.SOURCE_NAME));
       FileScheduleDTO config = (FileScheduleDTO) context.getJobDetail().getJobDataMap().get(String.valueOf(JobDataStore.DATA_STORE));
       Path path = Path.of(config.getConfigFileDTO().getPath());
       File file = new File(path.toUri());
       if(file.exists()) {
           if (type.equals("csv")) {
               fileCSVSource.read(config.getConfigFileDTO());
           }
           String fileName = file.getName();
           Path sentdir = Path.of(config.getSentdir() + "\\" + fileName);
           System.out.println("Job done succesfully");
           Files.move(path, sentdir, StandardCopyOption.REPLACE_EXISTING);

       }


    }
}
