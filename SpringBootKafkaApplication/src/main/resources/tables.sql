CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL PRIMARY KEY,
    firstname  VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
	age SMALLINT CHECK (age > 0) NULL
);

CREATE TABLE IF NOT EXISTS comments
(
    comment_id BIGSERIAL PRIMARY KEY,
	user_id BIGSERIAL REFERENCES users (user_id),
    comment_txt VARCHAR(255) NULL,
	comment_timestamp BIGINT NULL,
	product VARCHAR(50) NULL
)
