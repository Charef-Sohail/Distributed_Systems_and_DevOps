package com.enset.activite_pratique_n4.query.controllers;

import com.enset.activite_pratique_n4.query.dto.AccountStatementResponseDTO;
import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.entities.AccountOperation;
import com.enset.activite_pratique_n4.query.queries.GetAccountStatementQuery;
import com.enset.activite_pratique_n4.query.queries.GetAllAccountsQuery;
import com.enset.activite_pratique_n4.query.queries.WatchEventQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @GetMapping(path = "/watch/{accountId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AccountOperation> watch(@PathVariable String accountId) {
        // subscriptionQuery permet de s'abonner aux futures mises à jour
        return queryGateway.subscriptionQuery(
                new WatchEventQuery(accountId),
                ResponseTypes.instanceOf(AccountOperation.class),
                ResponseTypes.instanceOf(AccountOperation.class)
        ).updates();
        // .updates() retourne le Flux des nouveaux événements en temps réel
    }
}
