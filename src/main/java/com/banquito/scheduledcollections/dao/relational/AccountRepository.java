package com.banquito.scheduledcollections.dao.relational;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.scheduledcollections.model.relational.Account;
import com.banquito.scheduledcollections.model.relational.AccountPK;

import java.math.BigInteger;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, AccountPK> {
  Optional<Account> findByAccountNumber(BigInteger accountNumber);
}
