package com.enset.activite_pratique_n4.commands.controllers;

import com.enset.activite_pratique_n4.commands.commands.CreateAccountCommand;
import com.enset.activite_pratique_n4.commands.commands.CreditAccountCommand;
import com.enset.activite_pratique_n4.commands.dto.AddNewAccountRequestDTO;
import com.enset.activite_pratique_n4.commands.dto.CreditAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/accounts")
public class AccountCommandController {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private EventStore eventStore;

    @PostMapping("/add")
    public CompletableFuture<String> addNewAccount(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.initialBalance(),
                request.currency()
        ));
        return response;
    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountDTO request){
        CompletableFuture<String> result = this.commandGateway.send(new CreditAccountCommand(
                request.accountId(),
                request.amount(),
                request.currency()
        ));
        return result;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }

}






















