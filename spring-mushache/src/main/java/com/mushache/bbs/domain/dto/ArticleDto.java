package com.mushache.bbs.domain.dto;

import com.mushache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String content;

//    public ArticleDto(String title, String content){
//        this.title = title;
//        this.content = content;
//    }

    public Article toEntity(){
        return new Article(id,title,content);
    }


    @Override
    public String toString() {
        return "ArticleDto{" +
//                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
