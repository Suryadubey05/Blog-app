package com.example.Blog_App.articles;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping("")
    String getArticles(){
        return "get all articles";
    }

    @GetMapping("/{id}")
    String getArticleById(@PathVariable("id") String id){
        return "get article by id: "+id;
    }

    @PostMapping("")
    String createArticles(){
        return "create article";
    }
}
