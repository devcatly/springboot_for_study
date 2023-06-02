package com.catly.service;

import com.catly.domain.Article;
import com.catly.dto.AddArticeRequest;
import com.catly.dto.UpdateArticlrRequest;
import com.catly.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이거나 @Notnull인곳에 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticeRequest addArticeRequest) {
        return blogRepository.save(addArticeRequest.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticlrRequest updateArticlrRequest) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("not found" + id));

        article.update(updateArticlrRequest.getTitle(), updateArticlrRequest.getContent());

        return article;
    }

}

