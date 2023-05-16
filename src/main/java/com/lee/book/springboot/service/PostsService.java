package com.lee.book.springboot.service;

import com.lee.book.springboot.domain.posts.Posts;
import com.lee.book.springboot.domain.posts.PostsRepository;
import com.lee.book.springboot.web.dto.PostsSaveRequestDto;
import com.lee.book.springboot.web.dto.PostsUpdateRequestDto;
import com.lee.book.springboot.web.dto.PostsresponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // final 로 선언된 놈들 생성자 만들어 준다~
public class PostsService {
    private final PostsRepository postsRepository; // 초기화 안하면 빨간줄 떠!!!!

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public PostsresponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        return new PostsresponseDto(posts);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // id 값에 해당하는 엔티티 검색
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
        // 수정
        posts.update(requestDto.getContent(), requestDto.getTitle());
        return posts.getId();
    }
}
