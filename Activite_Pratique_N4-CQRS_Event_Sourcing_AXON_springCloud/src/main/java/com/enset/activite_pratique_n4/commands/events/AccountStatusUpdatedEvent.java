package com.enset.activite_pratique_n4.commands.events;

import com.enset.activite_pratique_n4.common.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@AllArgsConstructor
public class AccountStatusUpdatedEvent {
    private String id;
    private AccountStatus status;
}
