package me.june.devblog.accounts;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:51
 **/
public class AccountDto {

    @Getter
    static class Response {
        private String userId;
        private String username;
        private String email;
    }

    @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static class Create {
        private String userId;
        private String username;
        private String password;
        private String email;

        @Builder
        public Create(String userId, String username, String password, String email) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }
}
