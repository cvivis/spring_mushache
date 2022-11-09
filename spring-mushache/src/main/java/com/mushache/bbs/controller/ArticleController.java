package com.mushache.bbs.controller;

import com.mushache.bbs.domain.dto.ArticleDto;
import com.mushache.bbs.domain.entity.Article;
//import com.mushache.bbs.repository.ArticleRepository;
import com.mushache.bbs.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles",articles);


        return "articles/list";
    }

    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }


    @GetMapping(value = "/new")
    public String newArticleForm(){
        return "articles/new";

    }

    @GetMapping("/{id}")
    public String select(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            // Optional.get() ---> Article
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            // Optional.get() ---> Article
            model.addAttribute("article", optArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message",String.format("%d가 없습니다." ,id));
            return "articles/error";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, ArticleDto articleDto, Model model){
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }


    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form){
        log.info(form.toString());

        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        Article saved = articleRepository.save(articleEntity);
        log.info("generatedId:{}", saved.getId());
        return "redirect:/articles/" + saved.getId();
    }

}
