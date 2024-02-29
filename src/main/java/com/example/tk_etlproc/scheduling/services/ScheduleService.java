package com.example.tk_etlproc.scheduling.services;

import com.example.tk_etlproc.api.DTO.schedule.BaseScheduleDTO;
import com.example.tk_etlproc.scheduling.JobDataStore;
import com.example.tk_etlproc.scheduling.jobs.FileReaderJob;
import com.example.tk_etlproc.scheduling.jobs.SqlServerReaderJob;
import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ScheduleService {

    private SchedulerFactoryBean schedulerFactoryBean;

    private HashMap<String, Class> jobMap;
    @Autowired
    public ScheduleService(SchedulerFactoryBean schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.jobMap = new HashMap<>();
        jobMap.put("SqlServer", SqlServerReaderJob.class);
        jobMap.put("csv", FileReaderJob.class);

    }

    public void  scheduleCreate(BaseScheduleDTO scheduleDTO) throws SchedulerException {



        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedStringJobDetail = RandomStringUtils.random(length, useLetters, useNumbers);
        String generatedStringTrigger = RandomStringUtils.random(length, useLetters, useNumbers);
        JobDetail jobDetail = JobBuilder.newJob(jobMap.get(scheduleDTO.getSourceName()))
                .withIdentity(generatedStringJobDetail)
                .build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(String.valueOf(JobDataStore.DATA_STORE), scheduleDTO);
        jobDataMap.put(String.valueOf(JobDataStore.SOURCE_NAME), scheduleDTO.getSourceName());




        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity( generatedStringTrigger,  "Trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(scheduleDTO.getTimeout()).repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }


}
