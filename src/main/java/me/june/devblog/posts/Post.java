package me.june.devblog.posts;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.june.devblog.accounts.Account;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-09
 * Time: 22:06
 **/
@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @ManyToOne
    private Account createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

    @Builder
    public Post(String title, String contents, LocalDateTime createdAt, Account createdBy, LocalDateTime updatedAt, Account updatedBy) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public void update(PostDto.Update updateDto) {
        this.title = updateDto.getTitle();
        this.contents = updateDto.getContents();
    }
}
