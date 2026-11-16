package com.enset.activite_pratique_n4.query.controllers;

import com.enset.activite_pratique_n4.query.dto.AccountStatementResponseDTO;
import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.queries.GetAccountStatementQuery;
import com.enset.activite_pratique_n4.query.queries.GetAllAccountsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
@AllArgsConstructor
public class AccountQueryController {

    private QueryGateway queryGateway;

    // Consulter tous les comptes
    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccounts() {
        return queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class)
        );
    }

    // Consulter le relevé d'un compte (historique des transactions)
    @GetMapping("/statement/{accountId}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String accountId) {
        return queryGateway.query(
                new GetAccountStatementQuery(accountId),
                ResponseTypes.instanceOf(AccountStatementResponseDTO.class)
        );
    }
}
