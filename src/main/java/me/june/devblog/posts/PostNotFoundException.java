package me.june.devblog.posts;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-18
 * Time: 22:25
 **/
@Getter
public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(String message) {
        super(message);
    }
}
