package com.banquito.scheduled.collections.process;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.banquito.scheduled.collections.config.BaseURLValues;
import com.banquito.scheduled.collections.dto.CollectionOrderDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ReadAndInsertTask implements Tasklet, StepExecutionListener{

    private final RestTemplate restTemplate;
    private final BaseURLValues baseURLs;
    private List<CollectionOrderDTO> collectionOrders;

    @Override
    public void beforeStep(StepExecution arg0) {

        log.info("Va a cargar los datos");
        //Crear metodo en servicio
        ResponseEntity<CollectionOrderDTO[]> response = this.restTemplate.getForEntity(
              baseURLs.getCmCollectionOrderURL() + "/account", CollectionOrderDTO[].class);
        CollectionOrderDTO[] objectArray = response.getBody();
        collectionOrders = Arrays.asList(objectArray);
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        HttpEntity<CollectionOrderDTO> requestCollectionOrder;
             
        for (CollectionOrderDTO collectionOrder : collectionOrders) {
             //cambiar url
            requestCollectionOrder = new HttpEntity<CollectionOrderDTO>(collectionOrder);
            this.restTemplate.put(baseURLs.getCmCollectionOrderURL() + "/payCollectionOrder", requestCollectionOrder);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        // TODO Auto-generated method stub
        return ExitStatus.COMPLETED;
    }
    
}
