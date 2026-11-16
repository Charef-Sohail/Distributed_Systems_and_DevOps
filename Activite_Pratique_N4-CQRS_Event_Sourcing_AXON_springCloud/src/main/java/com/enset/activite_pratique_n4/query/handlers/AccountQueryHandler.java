package com.enset.activite_pratique_n4.query.handlers;

import com.enset.activite_pratique_n4.query.dto.AccountStatementResponseDTO;
import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.entities.AccountOperation;
import com.enset.activite_pratique_n4.query.queries.GetAccountStatementQuery;
import com.enset.activite_pratique_n4.query.queries.GetAllAccountsQuery;
import com.enset.activite_pratique_n4.query.repositories.AccountRepository;
import com.enset.activite_pratique_n4.query.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountQueryHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query) {
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());

        return new AccountStatementResponseDTO(account, operations);
    }
}
