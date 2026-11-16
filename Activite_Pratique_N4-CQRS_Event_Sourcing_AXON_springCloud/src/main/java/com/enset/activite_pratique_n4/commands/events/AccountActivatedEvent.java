package com.enset.activite_pratique_n4.commands.events;

import com.enset.activite_pratique_n4.common.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class AccountActivatedEvent {
    private String accountId;
    private AccountStatus status;
}
