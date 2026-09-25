package com.enset.bankaccountservice.service;

import com.enset.bankaccountservice.dto.BankAccountRequestDTO;
import com.enset.bankaccountservice.dto.BankAccountResponseDTO;
import com.enset.bankaccountservice.entities.BankAccount;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
