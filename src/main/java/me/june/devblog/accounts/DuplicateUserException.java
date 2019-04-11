package me.june.devblog.accounts;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-09
 * Time: 22:03
 **/
@Getter
public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String message) {
        super(message);
    }
}
