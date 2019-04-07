package me.june.devblog;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.june.devblog.accounts.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void list() throws Exception {
        ResultActions results = this.mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON_UTF8));

        results.andDo(print());
        results.andExpect(status().isOk());
    }

    private Account createAccount() {
        Account account = new Account();
        account.setUserId("ces518");
        account.setPassword("pjy3859");
        account.setUsername("jyPark");
        account.setEmail("ces518@mayeye.net");
        return account;
    }

    @Test
    public void create() throws Exception {
        ResultActions results = this.mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(createAccount())));

        results.andDo(print());
        results.andExpect(status().isCreated());
        results.andExpect(jsonPath("$.userId").value("ces518"));
        results.andExpect(jsonPath("$.username").value("jyPark"));
        results.andExpect(jsonPath("$.email").value("ces518@mayeye.net"));
    }

}
