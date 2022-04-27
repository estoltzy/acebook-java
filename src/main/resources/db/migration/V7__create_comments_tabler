CREATE TABLE comment (
  id bigserial PRIMARY KEY,
  comment_content varchar(250) NOT NULL,
  post_id bigint,
  FOREIGN KEY (post_id) REFERENCES posts(id)
);