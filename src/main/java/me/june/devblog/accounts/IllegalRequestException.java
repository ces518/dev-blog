package me.june.devblog.accounts;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 22:04
 **/
@Getter
public class IllegalRequestException extends RuntimeException {

    private String userId;

    public IllegalRequestException(String userId) {
        this.userId = userId;
    }
}
