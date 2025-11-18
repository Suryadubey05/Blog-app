package com.example.Blog_App.comments;

import com.example.Blog_App.articles.ArticlesEntity;
import com.example.Blog_App.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "articleId", nullable = false)
    private ArticlesEntity articles;


    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;
}
