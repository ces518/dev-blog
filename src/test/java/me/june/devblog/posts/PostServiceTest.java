package me.june.devblog.posts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void findAll() {
        Page<Post> all = postService.findAll(0);
        all.getContent();
    }

    @Test
    public void create() {
        PostDto.Create createDto = PostDto.Create.builder()
                                            .title("제목")
                                            .contents("내용").build();
        Post post = postService.createPost(createDto);

        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(createDto.getTitle());
        assertThat(post.getContents()).isEqualTo(createDto.getContents());
    }
}