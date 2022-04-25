package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  @Query("SELECT users.username, posts.content FROM posts INNER JOIN users ON posts.user_id=users.id;")
  public static List<Post> listPostsWithUsername() {
    return listPostsWithUsername();
  }

}
