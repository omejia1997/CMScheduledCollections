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
import org.springframework.web.client.RestTemplate;

import com.banquito.scheduledcollections.process.ProcessDataTask;
import com.banquito.scheduledcollections.process.ReadAndInsertTask;
import com.banquito.scheduledcollections.service.CollectionOrderService;
import com.banquito.scheduledcollections.service.SequenceService;
import com.banquito.scheduledcollections.service.relational.TransactionService;

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
    private TransactionService transactionService;

    @Autowired
    private BaseURLValues baseURLs;

    @Autowired
    private SequenceService sequenceService;
    
    @Bean
    protected Step readAndInsertTask() {
        return steps
                .get("readAndInsertTask")
                .tasklet(new ReadAndInsertTask(collectionOrderService,transactionService,baseURLs,sequenceService))
                .build();
    }

    @Bean
    protected Step processDataTask() {
        return steps
                .get("processDataTask")
                .tasklet(new ProcessDataTask())
                .build();
    }

    @Bean
    public Job processTextFileJob() {
        return jobs
                .get("processTextFileJob")
                .start(readAndInsertTask())
                .next(processDataTask())
                .build();
    }
}
