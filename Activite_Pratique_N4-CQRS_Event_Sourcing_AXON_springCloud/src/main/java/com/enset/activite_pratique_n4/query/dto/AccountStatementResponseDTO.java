package com.enset.activite_pratique_n4.query.dto;

import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.entities.AccountOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountStatementResponseDTO {
    private Account account;
    private List<AccountOperation> operations;
}