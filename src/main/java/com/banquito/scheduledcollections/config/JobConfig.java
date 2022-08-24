package com.banquito.scheduledcollections.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import com.banquito.scheduledcollections.process.ReadAndInsertTask;
import com.banquito.scheduledcollections.service.CollectionOrderService;
import com.banquito.scheduledcollections.service.SequenceService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {

     @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private CollectionOrderService collectionOrderService;

    @Autowired
    private BaseURLValues baseURLs;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    @Bean
    protected Step readAndInsertTask() {
        return steps
                .get("readAndInsertTask")
                .tasklet(new ReadAndInsertTask(collectionOrderService,baseURLs,sequenceService,kafkaTemplate))
                .build();
    }

    @Bean
    public Job processTextFileJob() {
        return jobs
                .get("processTextFileJob")
                .start(readAndInsertTask())
                .build();
    }
}
