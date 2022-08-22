package com.banquito.scheduledcollections.process;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.banquito.scheduledcollections.model.CollectionOrder;;

public class ProcessDataTask implements Tasklet, StepExecutionListener {

    private List<CollectionOrder> collectionOrders;
    
    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
          .getJobExecution()
          .getExecutionContext();
        this.collectionOrders = (List<CollectionOrder>) executionContext.get("collectionOrders");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for(CollectionOrder c: collectionOrders)
            System.out.println( "\nTarea2: "+c.toString());
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
