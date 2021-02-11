package com.salesken.ai.controller;

import com.salesken.ai.dto.AccountDTO;
import com.salesken.ai.entity.Account;
import com.salesken.ai.exception.CustomExceptionHandler;
import com.salesken.ai.service.AccountService;
import com.salesken.ai.util.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping(path = "/byId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long id){
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Account account = accountService.getAccountById(id);
        if(account == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountMapper.getAccountDTOFromAccount(account),HttpStatus.OK);
    }

    @GetMapping(path = "/byAccNo/{accNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getAccountByAccNo(@PathVariable("accNo") String accNo){
        if(accNo == null || accNo.trim().equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Account account = accountService.getAccountByAccNo(accNo);
        if(account == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(accountMapper.getAccountDTOFromAccount(account),HttpStatus.OK);
    }

    @PostMapping(path = "/save/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO accountDTO){
        if(accountDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return saveAccountDetails(accountDTO);
    }

    @PutMapping(path = "/update/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO){
        if(accountDTO == null || accountDTO.getId()==0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return saveAccountDetails(accountDTO);
    }

    @DeleteMapping(path = "/delete/{accNo}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> deleteAccountByAccNo(@PathVariable("accNo") String accNo){
        if(accNo==null || accNo.trim().equals(""))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Account account = accountService.deleteAccount(accNo);

        return (account!=null)
                ?new ResponseEntity<AccountDTO>(accountMapper.getAccountDTOFromAccount(account),HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    private ResponseEntity<AccountDTO> saveAccountDetails(AccountDTO accountDTO){
        Account account = accountMapper.getAccountFromDTO(accountDTO);
        if(account != null){
            account = accountService.saveAccount(account);
            if(account!=null)
                accountDTO = accountMapper.getAccountDTOFromAccount(account);
        }

        return (accountDTO.getId()!=0)
                ?new ResponseEntity<>(accountDTO,HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

}
