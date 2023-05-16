package com.lee.book.springboot.web.dto;

import com.lee.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsresponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsresponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author =  posts.getAuthor();
    }
}
