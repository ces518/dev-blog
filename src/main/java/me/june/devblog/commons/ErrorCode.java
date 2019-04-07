package me.june.devblog.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 22:14
 **/
@Getter @AllArgsConstructor @ToString
public enum  ErrorCode {
    BAD_REQUEST("400","잘못된 요청입니다.");

    private String errorCode;
    private String message;

}
