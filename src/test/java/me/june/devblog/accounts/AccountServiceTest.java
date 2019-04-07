package me.june.devblog.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountService.class)
public class AccountServiceTest {

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    private List createAccounts() {
        Account account = new Account();
        account.setId(1L);
        account.setEmail("ces518@mayeye.net");
        account.setUserId("ces518");
        account.setUsername("pjy");

        Account account2 = new Account();
        account2.setId(2L);
        account2.setEmail("ces518@mayeye2.net");
        account2.setUserId("ces5182");
        account2.setUsername("pjy2");

        return Arrays.asList(account,account2);
    }

    @Test
    public void list() {
        //given
        given(accountRepository.findAll()).willReturn(createAccounts());

        //when
        List<Account> accounts = accountService.findAll();

        //then
        assertThat(accounts.size()).isEqualTo(2);
    }


}