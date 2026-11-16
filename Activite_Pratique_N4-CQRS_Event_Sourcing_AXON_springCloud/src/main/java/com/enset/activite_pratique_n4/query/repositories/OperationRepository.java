package com.enset.activite_pratique_n4.query.repositories;

import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByAccountId(String accountId);
}