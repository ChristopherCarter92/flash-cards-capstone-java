BEGIN TRANSACTION;

DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id serial,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE cards (

    id serial,
    question varchar(200) NOT NULL,
    answer varchar(400) NOT NULL,
    tags varchar(50) NOT NULL,
    user_id int,
    CONSTRAINT PK_card PRIMARY KEY (id)
);

    ALTER TABLE cards ADD FOREIGN KEY (user_id) REFERENCES users(user_id);



COMMIT TRANSACTION;
