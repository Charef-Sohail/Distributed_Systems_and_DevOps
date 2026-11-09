package com.enset.activite_pratique_n4.commands.aggregates;

import com.enset.activite_pratique_n4.commands.commands.CreateAccountCommand;
import com.enset.activite_pratique_n4.commands.commands.CreditAccountCommand;
import com.enset.activite_pratique_n4.commands.events.AccountCreatedEvent;
import com.enset.activite_pratique_n4.commands.events.AccountCreditedEvent;
import com.enset.activite_pratique_n4.common.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private AccountStatus status;

    // pour axon
    public AccountAggregate(){

    }

    // subscribe pour le bus de commande
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        //les regles metiers
        log.info("################# AddAccountCommand received ############");
        if(command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance must be positive!");

        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
    }


    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("################# AddAccountCommand occurred ############");
        this.accountId = event.getAccountId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }

    public void handle(CreditAccountCommand command) {
        log.info("############ CreditAccountCommand Received ############");

        if (!status.equals(AccountStatus.ACTIVATED))
            throw new RuntimeException("The account " + command.getId() + " is not activated");

        if (command.getAmount() <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("################# AccountCreditedEvent occurred ############");
        this.accountId = event.getAccountId();
        this.balance += event.getAmount();
    }
}
