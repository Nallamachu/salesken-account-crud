package com.salesken.ai;

import com.salesken.ai.entity.Account;
import com.salesken.ai.repository.AccountRepository;
import com.salesken.ai.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CrudApplicationTests {

	@MockBean
	AccountService accountService;

	@Test
	void saveAccount(){
		Account account = getAccount();
		Mockito.when(accountService.saveAccount(account)).thenAnswer(AssertionError::new);
	}

	@Test
	void getAccountById(){
		Mockito.when(accountService.getAccountById(1l)).thenAnswer(AssertionError::new);
	}

	@Test
	void getAllAccount(){
		Mockito.when(accountService.getAllActiveAccounts()).thenAnswer(AssertionError::new);
	}

	private Account getAccount(){
		Account account = new Account();
		account.setActive(true);
		account.setAccNo("TEST001");
		account.setBranch("DUBAI");
		account.setIfsc("D001");
		return account;
	}

}
