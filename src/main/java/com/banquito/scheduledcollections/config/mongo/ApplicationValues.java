package com.banquito.scheduledcollections.config.mongo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationValues {
  private final String mongoHost;
  private final String mongoDB;
  private final String mongoUsr;
  private final String mongoPwd;
  private final String mongoAut;

  public ApplicationValues(
      @Value("${banquito.cmscheduledcollections.mongo.host}") String mongoHost,
      @Value("${banquito.cmscheduledcollections.mongo.db}") String mongoDB,
      @Value("${banquito.cmscheduledcollections.mongo.usr}") String mongoUsr,
      @Value("${banquito.cmscheduledcollections.mongo.pwd}") String mongoPwd,
      @Value("${banquito.cmscheduledcollections.mongo.aut}") String mongoAut) {

    this.mongoHost = mongoHost;
    this.mongoDB = mongoDB;
    this.mongoUsr = mongoUsr;
    this.mongoPwd = mongoPwd;
    this.mongoAut = mongoAut;
  }
}
