ALTER TABLE posts ADD user_id bigint;
ALTER TABLE posts ADD FOREIGN KEY (user_id) REFERENCES users(id);