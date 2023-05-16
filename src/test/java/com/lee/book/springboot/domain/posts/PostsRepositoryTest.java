package com.lee.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {
    @Autowired // 외부에서 의존성을 주입할때 쓰는 놈
    PostsRepository postsRepository;

    @AfterEach // 초기화를 담당하는 친구야
    public void Initialize(){
        postsRepository.deleteAll();
    }
    @Test
    public void isSave() {
        // test 할 때 이 세놈 기억해라 : given, when, then

        // given
        Posts posts = Posts.builder()
                .title("a title")
                .content("a content")
                .author("a author")
                .build();
        postsRepository.save(posts); // 이제 db 에 넣어보자~~

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        assertEquals(all.get(0).getTitle(), "a title"); // assertEquals(실제값, 기대값);
    }
}