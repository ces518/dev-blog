package me.june.devblog.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.june.devblog.commons.ErrorCode;
import me.june.devblog.commons.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:26
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accounts;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity getAccounts() {
        List<Account> list = accounts.findAll();
        List<AccountDto.Response> results = list.stream().map(account -> objectMapper.convertValue(account, AccountDto.Response.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody AccountDto.Create dto) {
        Account account = this.accounts.createAccount(dto);
        AccountDto.Response response = objectMapper.convertValue(account, AccountDto.Response.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity updateAccout(@RequestBody AccountDto.Update dto) {
        accounts.updateAccount(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id, Account account) {
        account.setId(id);
        accounts.deleteAccount(account);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("login")
    public ResponseEntity login(@RequestBody Account account, HttpSession session) {
        Account findedAccount = accounts.findAccount(account);
        session.setAttribute("SESSIONED_ACCOUNT",findedAccount);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ErrorResponse duplicateUserException(DuplicateUserException e) {
        return ErrorResponse.of(ErrorCode.BAD_REQUEST,e.getMessage());
    }
}
