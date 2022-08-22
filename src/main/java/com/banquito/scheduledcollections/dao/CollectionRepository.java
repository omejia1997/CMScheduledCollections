package com.banquito.scheduledcollections.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.scheduledcollections.model.Collection;

public interface CollectionRepository extends MongoRepository<Collection, String> {
  Optional<Collection> findByInternalId(String internalId);

  List<Collection> findByGroupInternalId(String groupInternalId);

  List<Collection> findByPeriodicityAndGroupInternalId(String periodicity, String groupInternalId);

  List<Collection> findByStateAndGroupInternalId(String state, String groupInternalId);

  List<Collection> findByServiceOfferedName(String name);

  List<Collection> findByServiceOfferedNameAndGroupInternalId(String name, String groupInternalId);
}
