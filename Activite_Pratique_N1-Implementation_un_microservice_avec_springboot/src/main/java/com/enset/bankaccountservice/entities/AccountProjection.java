package com.enset.bankaccountservice.entities;

import com.enset.bankaccountservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class)
public interface AccountProjection {
    public String getId();
    public AccountType getType();
}
