package com.banquito.scheduledcollections.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.banquito.scheduledcollections.dao.CollectionRepository;
import com.banquito.scheduledcollections.model.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionService {
  private final CollectionRepository collectionRepository;

  public List<Collection> findAll() {
    return this.collectionRepository.findAll();
  }

  public List<Collection> findByGroupInternalId(String groupInternalId) {
    return this.collectionRepository.findByGroupInternalId(groupInternalId);
  }

  public List<Collection> findByPeriodicityAndGroupInternalId(
      String periodicity, String internalId) {
    return this.collectionRepository.findByPeriodicityAndGroupInternalId(periodicity, internalId);
  }

  public List<Collection> findByStateAndGroupInternalId(String state, String internalId) {
    return this.collectionRepository.findByStateAndGroupInternalId(state, internalId);
  }

  public List<Collection> findByServiceOfferedNameAndGroupInternalId(
      String name, String internalId) {
    return this.collectionRepository.findByServiceOfferedNameAndGroupInternalId(name, internalId);
  }

}
