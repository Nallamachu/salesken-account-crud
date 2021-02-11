package com.salesken.ai.service.impl;

import com.salesken.ai.repository.AccountRepository;
import com.salesken.ai.entity.Account;
import com.salesken.ai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return (optionalAccount.isPresent())?optionalAccount.get():null;
    }

    @Override
    public Account getAccountByAccNo(String accNo) {
         return accountRepository.findByAccNoAndActiveIsTrue(accNo);
    }

    @Override
    public List<Account> getAllActiveAccounts() {
        List<Account> accounts = accountRepository.findAllActiveIsTrue();
        return (!CollectionUtils.isEmpty(accounts))?accounts:null;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccount(String accNo) {
        Account account = getAccountByAccNo(accNo);
        if(account!=null){
            account.setActive(false);
            account = accountRepository.save(account);
        }
        return account;
    }
}
