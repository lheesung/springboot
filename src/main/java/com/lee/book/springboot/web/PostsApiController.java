package com.lee.book.springboot.web;

import com.lee.book.springboot.domain.posts.Posts;
import com.lee.book.springboot.service.PostsService;
import com.lee.book.springboot.web.dto.PostsSaveRequestDto;
import com.lee.book.springboot.web.dto.PostsUpdateRequestDto;
import com.lee.book.springboot.web.dto.PostsresponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    // 게시판 상세 조회 기능
    @GetMapping("/api/v1/posts/{id}")
    public PostsresponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
}
