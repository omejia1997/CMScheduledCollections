package com.banquito.scheduled.collections.config;

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

import com.banquito.scheduled.collections.process.ReadAndInsertTask;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private BaseURLValues baseURLs;
    
    @Bean
    protected Step readAndInsertTask() {
        return steps
                .get("readAndInsertTask")
                .tasklet(new ReadAndInsertTask(restTemplate,baseURLs))
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
