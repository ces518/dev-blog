package me.june.devblog.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-07
 * Time: 21:24
 **/
@Entity
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Long id;
    private String userId;
    private String password;
    private String username;
    private String email;
}
