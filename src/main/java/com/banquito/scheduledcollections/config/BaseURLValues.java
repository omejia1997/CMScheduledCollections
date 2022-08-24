package com.banquito.scheduledcollections.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BaseURLValues {
  private final String coreAccountsTransactionsURL;

  private final String cmAccountingJournalEntryURL;

  private final String cmClientsAccountsURL;

  private final String cmAccountingGeneralLedgerAccountURL;

  public BaseURLValues(
      @Value("${banquito.core.accounts.base-url}") String coreAccountsURL,
      @Value("${banquito.cm.accounting.base-url}") String cmAccountingURL,
      @Value("${banquito.cm.clients.base-url}") String cmClientsURL,
      @Value("${banquito.cm.accounting.base-url}") String cmAccountingGeneralLedgerURL) {
    this.coreAccountsTransactionsURL = coreAccountsURL;
    this.cmAccountingGeneralLedgerAccountURL =
        cmAccountingGeneralLedgerURL + "/GeneralLedgerAccount";
    this.cmAccountingJournalEntryURL = cmAccountingURL + "/JournalEntry";
    this.cmClientsAccountsURL = cmClientsURL + "/account";
  }
}