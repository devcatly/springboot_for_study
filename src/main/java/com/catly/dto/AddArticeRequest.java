package com.catly.dto;

import com.catly.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor//기본생성자 추가
@AllArgsConstructor //모든값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticeRequest {

    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
