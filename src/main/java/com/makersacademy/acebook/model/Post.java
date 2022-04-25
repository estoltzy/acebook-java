package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "Post is mandatory")
    private String content;
    @Column(name="created_at")
	private Timestamp createdAt;
    @Column(name="like_count")
    private int likeCount = 0;
    @Column(nullable = true, length = 250)
    private String photos;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public Long getId() { return this.id; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt; }

    public void addLike() {
        likeCount++;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
