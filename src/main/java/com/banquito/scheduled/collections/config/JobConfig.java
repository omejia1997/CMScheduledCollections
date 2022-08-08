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

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {
    
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;
    /*
    @Bean
    protected Step readAndInsertTask() {
        return steps
                .get("readAndInsertTask")
                .tasklet(new ReadAndInsertTask())
                .build();
    }

    @Bean
    protected Step reverseDataTask() {
        return steps
                .get("reverseDataTask")
                .tasklet(new ReverseDataTask(this.mongoTemplate))
                .build();
    }

    @Bean
    protected Step summaryTask() {
        return steps
                .get("summaryTask")
                .tasklet(new SummaryTask(this.mongoTemplate))
                .build();
    }

    @Bean
    public Job processTextFileJob() {
        return jobs
                .get("processTextFileJob")
                .start(readAndInsertTask())
                .next(reverseDataTask())
                .next(summaryTask())
                .build();
    }
    */

}
