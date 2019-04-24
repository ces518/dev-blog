package me.june.devblog.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


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
        PageRequest pageRequest = PageRequest.of(page,10, Sort.Direction.DESC, "id");
        return postRepository.findAll(pageRequest);
    }

    public Post createPost(PostDto.Create createDto) {
        Post post = createDto.toEntity();
        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(PostDto.Update updateDto) {
        Post post = findPost(updateDto.getSeq());
        post.update(updateDto);
    }

    public Post findPost(Long id) {
        Assert.notNull(id,"id should be not null");

        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id + " 에 해당하는 게시물은 존재하지않습니다."));
    }

    @Transactional
    public void deletePost(Long seq) {
        Post post = findPost(seq);
        postRepository.delete(post);
    }
}
