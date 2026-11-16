package com.enset.activite_pratique_n4.query.handlers;

import com.enset.activite_pratique_n4.commands.events.*;
import com.enset.activite_pratique_n4.common.OperationType;
import com.enset.activite_pratique_n4.query.entities.Account;
import com.enset.activite_pratique_n4.query.entities.AccountOperation;
import com.enset.activite_pratique_n4.query.queries.WatchEventQuery;
import com.enset.activite_pratique_n4.query.repositories.AccountRepository;
import com.enset.activite_pratique_n4.query.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AccountEventHandler {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    private QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("Query Side : AccountCreatedEvent received");

        Account account = new Account();
        account.setId(event.getAccountId());
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());

        account.setCreatedAt(eventMessage.getTimestamp());

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event) {
        log.info("Query Side : AccountActivatedEvent received");
        Account account = accountRepository.findById(event.getAccountId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountStatusUpdatedEvent event) {
        log.info("Query Side : AccountStatusUpdatedEvent received");
        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }


    // Projection de l'événement de Débit
    @EventHandler
    public void on(AccountDebitedEvent event, EventMessage<AccountDebitedEvent> eventMessage) {
        log.info("Query Side : AccountDebitedEvent received");

        // 1. Charger le compte depuis la base de lecture
        Account account = accountRepository.findById(event.getAccountId()).get();

        // 2. Créer l'opération
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(event.getAmount());
        accountOperation.setOperationDate(eventMessage.getTimestamp());
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAccount(account);

        // 3. Sauvegarder l'opération
        operationRepository.save(accountOperation);

        // 4. Mettre à jour le solde du compte
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);
        // <-- LIGNE À AJOUTER POUR LE TEMPS RÉEL -->
        // On émet l'opération qui vient d'être sauvegardée à tous ceux qui écoutent ce compte
        queryUpdateEmitter.emit(
                WatchEventQuery.class,
                query -> query.getAccountId().equals(event.getAccountId()),
                accountOperation
        );
    }

    // Projection de l'événement de Crédit
    @EventHandler
    public void on(AccountCreditedEvent event, EventMessage<AccountCreditedEvent> eventMessage) {
        log.info("Query Side : AccountCreditedEvent received");

        Account account = accountRepository.findById(event.getAccountId()).get();

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(event.getAmount());
        accountOperation.setOperationDate(eventMessage.getTimestamp());
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAccount(account);

        operationRepository.save(accountOperation);

        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);
    }


}