package com.enset.bankaccountservice.service;

import com.enset.bankaccountservice.dto.BankAccountRequestDTO;
import com.enset.bankaccountservice.dto.BankAccountResponseDTO;
import com.enset.bankaccountservice.entities.BankAccount;
import org.springframework.graphql.data.method.annotation.Argument;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccountDTO);
}
