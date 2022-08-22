package com.banquito.scheduledcollections;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ScheduledcollectionsApplication {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
    @Qualifier("processTextFileJob")
    Job job1;
	public static void main(String[] args) {
		SpringApplication.run(ScheduledcollectionsApplication.class, args);
	}

	@Scheduled(fixedDelay = 10000, initialDelay = 10000)
	//@Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception {
        System.out.println("Iniciando el JOb");
        JobParameters params = new JobParametersBuilder()
                .addString("processTextFile", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(job1, params);
    }
}
    

    


	
