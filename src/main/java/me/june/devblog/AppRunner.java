package me.june.devblog;

import me.june.devblog.posts.Post;
import me.june.devblog.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-22
 * Time: 23:24
 **/
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 30; i++) {
            postRepository.save(Post.builder()
                    .title("제목" + i)
                    .contents("내용" + i).build());
        }

    }
}
