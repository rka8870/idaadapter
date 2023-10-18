package com.example.idaadapter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class EventListener {

    @Autowired
    private KafkaService kafkaService;

    @KafkaListener(topics = "task-assignment-topic",groupId = "group-1")
    public void listener(String value){
            log.info("message=received_message_ida-adapter value={}",value);
            kafkaService.send("task-completion-topic","{task-completed}");
            log.info("message=sent_message_to_workflow_automation_service value={}",value);
    }


}
