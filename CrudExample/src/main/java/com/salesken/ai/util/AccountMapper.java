package com.salesken.ai.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesken.ai.dto.AccountDTO;
import com.salesken.ai.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    ObjectMapper mapper = new ObjectMapper();

    public Account getAccountFromDTO(AccountDTO accountDTO){
        return getMapperInstance().convertValue(accountDTO, Account.class);
    }

    public AccountDTO getAccountDTOFromAccount(Account account){
        return getMapperInstance().convertValue(account, AccountDTO.class);
    }

    private ObjectMapper getMapperInstance(){
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
