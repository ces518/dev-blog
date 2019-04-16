package me.june.devblog.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:25
 **/
@Slf4j
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

    public Account findAccount(Account account) {
        Assert.notNull(account.getUserId(),"userid should be not null");
        Account accounts = this.accounts.findByUserId(account.getUserId());
        if(accounts == null) {
            throw new NotFoundAccountException(account.getUserId() + "는 존재하지않는 사용자입니다.");
        }
        return accounts;
    }

    public Account createAccount(AccountDto.Create createDto) {
        Account account = objectMapper.convertValue(createDto,Account.class);
        if(findAccount(account) != null) {
            throw new DuplicateUserException(account.getUserId() + "는 중복된 아이디입니다.");
        }
        Account savedAccount = accounts.save(account);
        return savedAccount;
    }

    public void updateAccount(AccountDto.Update updateDto) {
        Assert.notNull(updateDto.getUserId(),"userid should be not null");
        Account account = findAccount(objectMapper.convertValue(updateDto, Account.class));
        account.update(updateDto);
    }

    public void deleteAccount(Account account) {
        Assert.notNull(account.getId(),"userid should be not null");
        Account findedAccount = findAccount(account);
        accounts.delete(findedAccount);
    }
}
