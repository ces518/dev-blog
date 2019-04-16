package me.june.devblog.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-14
 * Time: 19:03
 **/
public interface PostRepository extends JpaRepository<Post,Long> {
}
