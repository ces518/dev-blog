package me.june.devblog.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:25
 **/
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accounts;

    @Autowired
    ObjectMapper objectMapper;

    public List<Account> findAll() {
        List<Account> list = accounts.findAll();
        return list;
    }

    public Account createAccount(AccountDto.Create createDto) {
        if(accounts.existsAccountByUserId(createDto.getUserId())) {
            throw new IllegalRequestException(createDto.getUserId() + "는 이미 존재하는 사용자입니다.");
        }

        Account account = objectMapper.convertValue(createDto, Account.class);
        Account savedAccount = accounts.save(account);
        return savedAccount;
    }
}
