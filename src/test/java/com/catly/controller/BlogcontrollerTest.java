package com.catly.controller;

import com.catly.domain.Article;
import com.catly.dto.AddArticeRequest;
import com.catly.dto.UpdateArticlrRequest;
import com.catly.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogcontrollerTest {
 @Autowired
 protected MockMvc mockMvc;
 @Autowired
 protected ObjectMapper objectMapper; //직렬화(java->json),역직릴화(json->java) 위한 클래스

 @Autowired
 private WebApplicationContext  context;

 @Autowired
    BlogRepository blogRepository;

 @BeforeEach
    public void mockMvcSetUp(){
     this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
             .build();
     blogRepository.deleteAll();
 }

 @DisplayName("addArticle: 블로그 글 추가에 성공한다. ")
 @Test
 public void addArticle() throws Exception{

  //given
 final String url = "/api/articles";
 final String title = "title";
 final String content = "content";
 final AddArticeRequest addArticeRequest = new AddArticeRequest(title, content);

 // 객체 json으로 직렬화
  final String requestBody = objectMapper.writeValueAsString(addArticeRequest);

  //when
  ResultActions resultActions = mockMvc.perform(post(url)
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(requestBody));
  //then
  resultActions.andExpect(status().isCreated());
  List<Article> articles = blogRepository.findAll();

  assertThat(articles.size()).isEqualTo(1);
  assertThat(articles.get(0).getTitle()).isEqualTo(title);
  assertThat(articles.get(0).getContent()).isEqualTo(content);

 }

 @DisplayName("findAllArticles: 블로그 글 조화")
 @Test
 public void findAllArticle() throws Exception{

  //given
  final String url = "/api/articles";
  final String title = "title";
  final String content = "content";

  blogRepository.save(Article.builder()
                  .title(title)
                  .content(content)
          .build());

 //when
  final  ResultActions resultActions = mockMvc.perform(get(url)
          .accept(MediaType.APPLICATION_JSON));

  resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].content").value(content))
          .andExpect(jsonPath("$[0].title").value(title));
 }

 @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
 @Test
 public void findArticle() throws Exception{
  //given
  final String url = "/api/articles/{id}";
  final String title = "title";
  final String content = "content";

  Article savedArticle = blogRepository.save(Article.builder()
                  .title(title)
                  .content(content)
          .build());

  final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

  resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.content").value(content))
          .andExpect(jsonPath("$.title").value(title));
 }

 @DisplayName("deleteArticle: 블로그 글 삭제성공")
 @Test
 public void deleteArticle() throws Exception {
  //given
  final String url = "/api/articles/{id}";
  final String title = "title";
  final String content = "content";

  Article savedArticle = blogRepository.save(Article.builder()
          .content(content)
                  .title(title)
          .build());

  //when
  mockMvc.perform(delete(url,savedArticle.getId()))
          .andExpect(status().isOk());

  //then
  List<Article> articles = blogRepository.findAll();

  assertThat(articles).isEmpty();
 }

 @DisplayName("updateAricle: 글 수정성공")
 @Test
 public void updateArticle() throws Exception{
  //given
  final String url = "/api/articles/{id}";
  final String title = "title";
  final String content = "content";

  Article savedArticle = blogRepository.save(Article.builder()
          .title(title)
          .content(content)
          .build());
  final String newTitle = "title";
  final String newContent = "content";

  UpdateArticlrRequest request= new UpdateArticlrRequest(newTitle, newContent);

  //when
  ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(objectMapper.writeValueAsString(request)));

  //then
  result.andExpect(status().isOk());

  Article article = blogRepository.findById(savedArticle.getId()).get();

  assertThat(article.getTitle()).isEqualTo(newTitle);
  assertThat(article.getContent()).isEqualTo(content);
 }
}