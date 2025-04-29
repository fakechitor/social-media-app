CREATE TABLE messages(
    id SERIAL PRIMARY KEY,
    sender_id INTEGER REFERENCES "user".users(id),
    receiver_id INTEGER REFERENCES "user".users(id),
    message VARCHAR(1000) NOT NULL,
    sent_at timestamp DEFAULT now()
);