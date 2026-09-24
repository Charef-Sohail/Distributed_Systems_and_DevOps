package com.enset.bankaccountservice.repositories;

import com.enset.bankaccountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccoutRepository extends JpaRepository<BankAccount, String> {

}
