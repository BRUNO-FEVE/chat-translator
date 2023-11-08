CREATE TABLE user (
    id TEXT NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    language CHAR(4) NOT NULL
);

CREATE TABLE chat (
    chat_id TEXT NOT NULL,
    user_one TEXT NOT NULL,
    user_two TEXT NOT NULL
);

CREATE TABLE messages (
    chat_id TEXT NOT NULL,
    message_position_number INT NOT NULL,
    message_owner_id TEXT NOT NULL,
    message TEXT NOT NULL,
    message_translated TEXT NOT NULL,
    time CHAR(5) NOT NULL
);
