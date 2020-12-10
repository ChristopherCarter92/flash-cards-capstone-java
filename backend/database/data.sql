BEGIN TRANSACTION;

INSERT INTO public.users (user_id, username, password_hash, role) VALUES (1, 'Test', '$2a$10$T.dutnoA.zFw5NBEM0NaC.y6kwrwLcYEisyZl/E3pcZ4mzDsb1k62', 'ROLE_USER');

INSERT INTO public.cards (card_id, question, answer, tags, user_id) VALUES (1, 'What is 1+1', '2', 'Math', 1);
INSERT INTO public.cards (card_id, question, answer, tags, user_id) VALUES (2, 'What is 1-1', '0', 'Math', 1);
INSERT INTO public.cards (card_id, question, answer, tags, user_id) VALUES (3, 'water', 'H2O', 'Science', 1);

INSERT INTO public.decks (deck_id, username, title) VALUES (1, 'Test', 'mathTestDeck');

INSERT INTO public.card_deck (deck_id, card_id) VALUES (1, 1);
INSERT INTO public.card_deck (deck_id, card_id) VALUES (1, 2);


SELECT pg_catalog.setval('public.cards_card_id_seq', 3, true);

COMMIT TRANSACTION;