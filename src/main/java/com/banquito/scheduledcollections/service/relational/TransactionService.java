package com.banquito.scheduledcollections.service.relational;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.banquito.scheduledcollections.dao.relational.AccountRepository;
import com.banquito.scheduledcollections.dto.relational.TransactionDTO;
import com.banquito.scheduledcollections.exception.NotFoundException;
import com.banquito.scheduledcollections.model.relational.Account;
import com.banquito.scheduledcollections.model.relational.AccountFamily;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
  private final AccountRepository accountRepository;
 
  public Boolean validateTransfer(TransactionDTO transactionDTO){
    Account creditorAccount =
        this.findAccountByAccountNumber(transactionDTO.getCreditorAccountNumber());
    Account debtorAccount =
        this.findAccountByAccountNumber(transactionDTO.getDebtorAccountNumber());
    AccountFamily debtorFamily = creditorAccount.getAccountFamily();
    AccountFamily creditorFamily = debtorAccount.getAccountFamily();

    if(this.validateWithdrawAmountAgainstFamily(transactionDTO.getAmount(), debtorFamily) || this.validateDepositAmountAgainstFamily(transactionDTO.getAmount(), creditorFamily))
      return true;
    return false;
  }

  private Account findAccountByAccountNumber(BigInteger accountNumber) {
    return this.accountRepository
        .findByAccountNumber(accountNumber)
        .orElseThrow(
            () ->
                new NotFoundException(
                    "No se ha encontrado una cuenta con el número " + accountNumber));
  }

  /*private Boolean validateAccountFunds(Account account, BigDecimal amount){
    boolean hasNotEnoughFunds = account.getBalance().compareTo(amount) < 0;
    if (hasNotEnoughFunds) {
      log.warn("La cuenta {} no tiene fondos suficientes.", account.getAccountNumber());
      return false;
    }
    return true;
  }*/

  public BigDecimal validateAccountFundsRecurrement(BigInteger accountNumber, BigDecimal amount){
    Account account =
        this.findAccountByAccountNumber(accountNumber);
	if(account.getBalance().compareTo(new BigDecimal(0)) > 0 && account.getBalance().compareTo(amount) < 0)
		return account.getBalance();
	else if(account.getBalance().compareTo(amount) > 0 || account.getBalance().compareTo(amount) == 0)
		return amount;
	else
      log.warn("La cuenta {} no tiene fondos suficientes.", account.getAccountNumber());
	return new BigDecimal(0);
  }

  private Boolean validateWithdrawAmountAgainstFamily(BigDecimal amount, AccountFamily debtorFamily){
    boolean amountIsLowerThanMin = amount.compareTo(debtorFamily.getMinWithdrawalAmount()) < 0;
    if (amountIsLowerThanMin) {
      log.warn("El monto del retiro es menor que el mínimo permitido por la familia de cuentas.");
      return false;
    }

    boolean amountIsGreaterThanMax = amount.compareTo(debtorFamily.getMaxWithdrawalAmount()) > 0;
    if (amountIsGreaterThanMax) {
      log.warn("El monto del retiro es mayor que el máximo permitido por la familia de cuentas.");
      return false;
    }
    return true;
  }

  private Boolean validateDepositAmountAgainstFamily(BigDecimal amount, AccountFamily creditorFamily){
    boolean depositAmountIsGreaterThanMax =
        amount.compareTo(creditorFamily.getMaxDepositAmount()) > 0;
    if (depositAmountIsGreaterThanMax) {
      log.warn("El monto de depósito es mayor que el máximo permitido por la familia de cuentas.");
      return false;
    }
    return true;
  }
}
