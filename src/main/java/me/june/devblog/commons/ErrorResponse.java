package me.june.devblog.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 22:13
 **/
@Getter @Setter
public class ErrorResponse implements Serializable {

    private ErrorCode errorCode;
    private String cause;

    public static ErrorResponse of(ErrorCode errorCode, String cause) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setCause(cause);
        return errorResponse;
    }
}
