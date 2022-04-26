
package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.makersacademy.acebook.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "COMMENT")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment_content;

    public Comment() {}

    public Comment(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getContent() { return this.comment_content; }
    public Long getId() { return this.id; }
    public void setCommentcontent(String comment_content) { this.comment_content = comment_content; }
    public void ssetCommentcontent(String comment_content) { this.comment_content = comment_content; }

    }