CREATE TABLE post.post_images(
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT REFERENCES post.posts(id),
    image_name VARCHAR(30),
    data BYTEA NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    content_type TEXT
);