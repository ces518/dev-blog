package me.june.devblog.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:26
 **/
public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsAccountByUserId(String userId);

    Account findByUserId(String userId);
}
