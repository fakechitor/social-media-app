CREATE TABLE subscriptions(
    id SERIAL PRIMARY KEY,
    subscriber_id INTEGER REFERENCES "user".users(id),
    subscribed_to_id INTEGER REFERENCES "user".users(id)
);