package com.example.tk_etlproc.api.controllers.scheduling;

import com.example.tk_etlproc.api.DTO.schedule.FileScheduleDTO;
import com.example.tk_etlproc.scheduling.services.ScheduleService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config/schedule/file")
public class ReadingFileScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ReadingFileScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/csv")
    public void fileCsvScheduleCreate(@RequestBody FileScheduleDTO scheduleDTO) throws SchedulerException {
        scheduleDTO.setSourceName("csv");
        scheduleService.scheduleCreate(scheduleDTO);
    }

}
