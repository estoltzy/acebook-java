package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.util.Set;

@Data
@Entity
@Table(name = "USERS")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;

    @Column(name="user_photo")
    private String photoLocation="https://i.pinimg.com/originals/7d/ad/68/7dad68f5621e8b7d675eac0bbb904c33.jpg";

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public String getPhotoLocation() {return this.photoLocation; }
    public void setPhotoLocation(String photoLocation) {this.photoLocation = photoLocation; }
}
