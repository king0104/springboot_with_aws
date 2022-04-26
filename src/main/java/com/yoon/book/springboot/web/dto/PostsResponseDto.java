package com.yoon.book.springboot.web.dto;

import com.yoon.book.springboot.domain.posts.Posts;
import lombok.Getter;

// 이건 왜 만든건지?
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
