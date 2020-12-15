CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL PRIMARY KEY,
    firstname  VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
	age SMALLINT CHECK (age > 0) NULL
);

CREATE TABLE IF NOT EXISTS messages
(
    message_id BIGSERIAL PRIMARY KEY,
	user_id BIGSERIAL REFERENCES users (user_id),
    message_txt VARCHAR(255) NULL,
	message_timestamp BIGINT NULL,
	device VARCHAR(50) NULL
)