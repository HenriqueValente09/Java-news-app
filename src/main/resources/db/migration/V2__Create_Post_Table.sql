CREATE TABLE post (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    imageURL TEXT NOT NULL,
    account_id TEXT NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    FOREIGN KEY (account_id) REFERENCES "account" (id)
);
