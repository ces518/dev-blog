package me.june.devblog.accounts;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-09
 * Time: 22:34
 **/
public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException(String message) {
        super(message);
    }
}
