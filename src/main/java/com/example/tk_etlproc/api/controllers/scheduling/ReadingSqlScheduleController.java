package com.example.tk_etlproc.api.controllers.scheduling;

import com.example.tk_etlproc.api.DTO.schedule.SqlScheduleDTO;
import com.example.tk_etlproc.scheduling.services.ScheduleService;
import org.quartz.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config/schedule/sql")
public class ReadingSqlScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    public ReadingSqlScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/sqlserver")
    public void sqlServerScheduleCreate(@RequestBody SqlScheduleDTO scheduleDTO) throws SchedulerException {
        scheduleDTO.setSourceName("SqlServer");
        scheduleService.scheduleCreate(scheduleDTO);
    }


}
