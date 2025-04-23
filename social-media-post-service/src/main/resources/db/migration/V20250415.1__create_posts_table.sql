CREATE TABLE post.posts(
    id BIGSERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL  REFERENCES "user".users(id),
    title VARCHAR(30),
    text VARCHAR(1000),
    created_at TIMESTAMP DEFAULT now()
);