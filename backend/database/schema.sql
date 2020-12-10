BEGIN TRANSACTION;

DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS card_deck;
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

    card_id serial,
    question varchar(200) NOT NULL,
    answer varchar(400) NOT NULL,
    tags varchar(50) NOT NULL,
    user_id int,
    CONSTRAINT PK_card PRIMARY KEY (card_id)
);

CREATE TABLE decks (

    deck_id serial,
    username varchar(50) NOT NULL,
    title varchar(50) NOT NULL,
    CONSTRAINT pk_decks PRIMARY KEY (deck_id)

);

CREATE TABLE card_deck (

    deck_id int,
    card_id int


);

    ALTER TABLE cards ADD FOREIGN KEY (user_id) REFERENCES users(user_id);
    ALTER TABLE card_deck ADD FOREIGN KEY (deck_id) REFERENCES decks(deck_id);
    ALTER TABLE card_deck ADD FOREIGN KEY (card_id) REFERENCES cards(card_id);




COMMIT TRANSACTION;
