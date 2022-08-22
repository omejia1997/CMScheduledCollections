package com.banquito.scheduledcollections.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.banquito.scheduledcollections.enums.SequenceEnum;
import com.banquito.scheduledcollections.model.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceService {
  private final MongoOperations mongoOperations;

  public final String getNextTN() {
    return getNextValue(SequenceEnum.TRANSACTION_NUMBER);
  }

  public final String getNextDN() {
    return getNextValue(SequenceEnum.DOCUMENT_NUMBER);
  }

  public final String getNextEO() {
    return getNextValue(SequenceEnum.EXTERNAL_OPERATION);
  }

  private String getNextValue(SequenceEnum sequence) {
    return sequence.format(getNextValue(sequence.getId()));
  }

  private Long getNextValue(String sequenceId) {
    Sequence sequence =
        mongoOperations.findAndModify(
            query(where("_id").is(sequenceId)),
            new Update().inc("value", 1),
            options().returnNew(true).upsert(true),
            Sequence.class);
    if (sequence == null) {
      sequence = new Sequence();
      sequence.setId(sequenceId);
      sequence.setValue(Long.valueOf(1));
      mongoOperations.insert(sequence);
    }
    return sequence.getValue();
  }
}
