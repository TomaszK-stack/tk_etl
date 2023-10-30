package com.example.tk_etlproc.streaming.listener;

import com.example.tk_etlproc.processing.OutputFromStep;
import com.example.tk_etlproc.reading.sources.sql.SqlSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class StreamListener {


    @KafkaListener(topics = "sql")
    public void listen(@Payload List data)
    {
        System.out.println(data);
    }
}
