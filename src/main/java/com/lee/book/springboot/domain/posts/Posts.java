package com.lee.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // Posts 클래스를 하나의 table 로 만들어줌 -> JPA 필요 -> repository
public class Posts {
    @Id // 바로 밑에 있는 놈이 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 바로 밑에 있는 놈이 column
    private  String title;

    @Column(nullable = false)
    private String content;

    private String author; // 얘는 db 에 넣기 싫어서 컬럼 키워드 빼줌
    // 요구사항
    // 번호, 제목, 내용,작성자 등 ...

    @Builder // 생성자를 통해 사용
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long update(String content, String title) {
        this.content = content;
        this.title = content;
        return this.id;
    }
}
