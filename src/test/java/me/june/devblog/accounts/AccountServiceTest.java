package me.june.devblog.accounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
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

    @Test
    public void create() {
        //given
        Account account = new Account();
        account.setId(1L);
        account.setEmail("ces518@mayeye.net");
        account.setUserId("ces518");
        account.setUsername("pjy");

        AccountDto.Create dto = AccountDto.Create.builder()
                .userId("ces518")
                .password("pjy3859")
                .username("jyPark")
                .email("ces518@mayeye.net").build();

        //when
        Account result = accountService.createAccount(dto);

        //then
        assertThat(result.getId()).isEqualTo("ces518");
    }

    @Test
    public void update() {
        AccountDto.Update dto = AccountDto.Update.builder()
                .userId("ces518")
                .password("pjy38592")
                .username("jyPark")
                .email("ces518@mayeye.net2").build();

        accountService.updateAccount(dto);
    }

    @Test
    public void delete() {
        Account account = new Account();
        account.setUserId("ces518");

        Throwable thrown = catchThrowable(()-> {
            accountService.deleteAccount(account);
        });

        assertThat(thrown)
                .isInstanceOf(NotFoundAccountException.class)
                .hasMessage(account.getUserId() + "는 존재하지않는 사용자입니다.");

    }

    @Test(expected = NotFoundAccountException.class)
    public void test() {
        Account account = new Account();
        account.setUserId("ces518");
        accountService.deleteAccount(account);
    }


}