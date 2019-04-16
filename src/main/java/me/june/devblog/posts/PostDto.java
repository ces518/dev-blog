package me.june.devblog.posts;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-04-16
 * Time: 21:58
 **/
public class PostDto {

    @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Create {
        @NotBlank(message = "제목은 빈 값이 올수없습니다.")
        private String title;

        @NotBlank(message = "내용은 빈 값이 올수없습니다.")
        private String contents;

        public Post toEntity() {
            return Post.builder()
                            .title(this.title)
                            .contents(this.contents).build();
        }
    }
}
