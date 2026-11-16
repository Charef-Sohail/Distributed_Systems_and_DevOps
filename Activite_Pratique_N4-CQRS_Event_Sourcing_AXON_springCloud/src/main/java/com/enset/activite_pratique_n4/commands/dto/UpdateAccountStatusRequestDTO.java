package com.enset.activite_pratique_n4.commands.dto;

import com.enset.activite_pratique_n4.common.AccountStatus;

public record UpdateAccountStatusRequestDTO(String accountId, AccountStatus status) {}