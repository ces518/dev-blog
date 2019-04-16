package me.june.devblog.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-14
 * Time: 19:03
 **/
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<Post> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(0,10, Sort.Direction.DESC, "id");
        return postRepository.findAll(pageRequest);
    }

    public Post createPost(PostDto.Create createDto) {
        Post post = createDto.toEntity();
        return postRepository.save(post);
    }
}
