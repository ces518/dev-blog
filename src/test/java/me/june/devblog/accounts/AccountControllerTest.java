package me.june.devblog.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    private List createAccounts() {
        Account account = new Account();
        account.setId(1L);
        account.setEmail("ces518@mayeye.net");
        account.setPassword("pjy3859");
        account.setUserId("ces518");
        account.setUsername("pjy");

        Account account2 = new Account();
        account2.setId(2L);
        account2.setEmail("ces518@mayeye2.net");
        account.setPassword("pjy3859");
        account2.setUserId("ces5182");
        account2.setUsername("pjy2");

        return Arrays.asList(account,account2);
    }

    private Account createAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setUserId("ces518");
        account.setPassword("pjy3859");
        account.setUsername("jyPark");
        account.setEmail("ces518@mayeye.net");
        return account;
    }

    @Test
    public void list() throws Exception {
        //given
        given(accountService.findAll()).willReturn(createAccounts());

        //when
        ResultActions result = this.mockMvc.perform(get("/accounts"));

        //then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].userId").value("ces518"));
        result.andExpect(jsonPath("$[0].username").value("pjy"));
        result.andExpect(jsonPath("$[0].email").value("ces518@mayeye.net"));
    }

    @Test
    public void create() throws Exception {
        //given
        Account account = createAccount();
        given(accountService.createAccount(any(AccountDto.Create.class))).willReturn(account);

        //when
        ResultActions result = this.mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(account)));

        //then
        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.userId").value("ces518"));
        result.andExpect(jsonPath("$.username").value("jyPark"));
        result.andExpect(jsonPath("$.email").value("ces518@mayeye.net"));
    }

    @Test
    public void delete() throws Exception {
        //given
        Account account = createAccount();
        given(accountService.createAccount(any(AccountDto.Create.class))).willReturn(account);

        //when
        ResultActions result = this.mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(account)));

        //then
        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.userId").value("ces518"));
        result.andExpect(jsonPath("$.username").value("jyPark"));
        result.andExpect(jsonPath("$.email").value("ces518@mayeye.net"));

        //when
        result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/" + account.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        result.andDo(print());
        result.andExpect(status().isNoContent());
    }

}