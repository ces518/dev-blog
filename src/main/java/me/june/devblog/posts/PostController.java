package me.june.devblog.posts;

import lombok.RequiredArgsConstructor;
import me.june.devblog.commons.ErrorCode;
import me.june.devblog.commons.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-14
 * Time: 19:03
 **/
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity getPosts(@RequestParam(required = false, defaultValue = "0") int page) {
        Page<Post> pagedPosts = postService.findAll(page);
        return ResponseEntity.ok(pagedPosts);
    }

    @PostMapping
    public ResponseEntity createPost(@Valid @RequestBody PostDto.Create createDto,
                                     BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST,"잘못된 요청입니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Post post = postService.createPost(createDto);
        return ResponseEntity.ok(post);
    }

    @PutMapping("{seq}")
    public ResponseEntity updatePost(@Valid @RequestBody PostDto.Update updateDto,
                                     BindingResult result) {
        if(result.hasErrors()) {
            ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST,"잘못된 요청입니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        postService.updatePost(updateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{seq}")
    public ResponseEntity deletePost(@PathVariable Long seq) {
        postService.deletePost(seq);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity postNotFoundException(PostNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST,e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
