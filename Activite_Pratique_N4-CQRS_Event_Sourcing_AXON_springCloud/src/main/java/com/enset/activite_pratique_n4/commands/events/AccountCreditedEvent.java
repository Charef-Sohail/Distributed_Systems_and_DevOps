package com.enset.activite_pratique_n4.commands.events;

import com.enset.activite_pratique_n4.common.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccountCreditedEvent {
    private String accountId;
    private double amount;
    private String currency;
}
