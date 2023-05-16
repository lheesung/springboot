package com.lee.book.springboot.web;

import com.lee.book.springboot.domain.posts.Posts;
import com.lee.book.springboot.domain.posts.PostsRepository;
import com.lee.book.springboot.web.dto.PostsSaveRequestDto;
import com.lee.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort // 랜덤적으로 포트를 받아옴
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void join() throws Exception{
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";
        // when
        ResponseEntity<Long>responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        // then - 비교분석하는
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK); // response entity 가 200이면 성공 -> 값 저장
        List<Posts> all = postsRepository.findAll();
        assertEquals(all.get(0).getTitle(),"title");
        assertEquals(all.get(0).getContent(), "content");
    }

    @Test
    public void edit() throws Exception {
        // given
        // 1. save
        join();
        // 2. update 를 위한 객체 생성
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title("hello")
                .content("hello~~~!!!")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts/1";
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        // when
        ResponseEntity<Long>responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        // 1. 적용
        // then
        // 1. 비교분석
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        List<Posts> all = postsRepository.findAll();
        assertEquals(all.get(0).getTitle(), "hello");
        assertEquals(all.get(0).getContent(), "hello~~~!!!");
    }
}