package com.banquito.scheduledcollections.process;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.math.RoundingMode;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.HttpEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

import com.banquito.scheduledcollections.config.BaseURLValues;
import com.banquito.scheduledcollections.dto.JournalDTO;
import com.banquito.scheduledcollections.dto.relational.AccountDTO;
import com.banquito.scheduledcollections.dto.relational.TransactionDTO;
import com.banquito.scheduledcollections.model.Collection;
import com.banquito.scheduledcollections.model.CollectionOrder;
import com.banquito.scheduledcollections.service.CollectionOrderService;
import com.banquito.scheduledcollections.service.SequenceService;
import com.banquito.scheduledcollections.service.relational.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ReadAndInsertTask implements Tasklet, StepExecutionListener {

    private List<CollectionOrder> collectionOrders;

    private final CollectionOrderService collectionOrderService;
    
    private final TransactionService transactionService;

    private final BaseURLValues baseURLs;

    private KafkaTemplate<String, Object> kafkaTemplate;

    private final SequenceService sequenceService;
    
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void beforeStep(StepExecution arg0) {
        collectionOrders = collectionOrderService.getCollectionOrderRecurrementToPay();
    }

    //Validar Fecha de dia sabado
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        BigDecimal fullPaymentValue = new BigDecimal(0);
        for (CollectionOrder collectionOrder : collectionOrders) {  
                if (collectionOrder.getPending().compareTo(new BigDecimal(0)) == 0) {
                    fullPaymentValue = collectionOrder.getAmount();
                } else {
                    fullPaymentValue = collectionOrder.getPending();
                }
                    Collection collectionDb = this.collectionOrderService.findCollectionById(collectionOrder.getCollectionId());

                    TransactionDTO transactionDTO =
                        TransactionDTO.builder()
                            .debtorAccountNumber(collectionOrder.getDebtorAccount())
                            .creditorAccountNumber(collectionDb.getCreditorAccount())
                            .amount(fullPaymentValue.setScale(2, RoundingMode.HALF_UP))
                            .reference(collectionOrder.getReference())
                            .channel("CM COBROS BANQUITO")
                            .serviceLevel("SEPA")
                            .externalOperation(this.sequenceService.getNextEO())
                            .documentNumber(this.sequenceService.getNextDN())
                            .transactionNumber(this.sequenceService.getNextTN())
                            .build();
                    this.kafkaTemplate.send("collections_recurrement", transactionDTO);
                    
                    /*if(this.transactionService.validateTransfer(transactionDTO)){
                        //
                        paidRecords = collectionDb.getPaidRecords();
                        paidRecords++;
                        collectionDb.setPaidRecords(paidRecords);
                        JournalDTO journalDTO =
                            JournalDTO.builder()
                                .journalId("")
                                .amount(collectionOrder.getAmount())
                                .transactionReference(transactionDTO.getTransactionNumber())
                                .transactionDate(new Date())
                                .type("COLLECTION")
                                .build();
                        HttpEntity<JournalDTO> journalTransaction = new HttpEntity<>(journalDTO);
                        JournalDTO journalCreated =
                            this.restTemplate.postForObject(
                                baseURLs.getCmAccountingJournalEntryURL(), journalTransaction, JournalDTO.class);
                        collectionOrder.setJournalId(journalCreated.getJournalId());
                        this.collectionOrderService.saveCollectionOrder(collectionOrder);
                        this.collectionOrderService.saveCollection(collectionDb);
                    }*/
        }
        return RepeatStatus.FINISHED;
    }

    

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        return ExitStatus.COMPLETED;
    }

}
