package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private int likesNumber = 0;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public Long getId() { return this.id; }
    public void setContent(String content) { this.content = content; }
    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt; }

    public Integer addLike() {

    }

    public Integer likeCount() {
        return likesNumber;
    }
}
