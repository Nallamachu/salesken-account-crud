package com.salesken.ai.service;

import com.salesken.ai.entity.Account;

import java.util.List;

public interface AccountService {
    public Account getAccountById(Long id);

    public Account getAccountByAccNo(String accNo);

    public List<Account> getAllActiveAccounts();

    public Account saveAccount(Account account);

    public Account deleteAccount(String accNo);
}
