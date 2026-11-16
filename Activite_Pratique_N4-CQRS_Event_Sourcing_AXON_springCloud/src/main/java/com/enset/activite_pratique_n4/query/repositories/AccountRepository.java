package com.enset.activite_pratique_n4.query.repositories;

import com.enset.activite_pratique_n4.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}