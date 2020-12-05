BEGIN TRANSACTION;

INSERT INTO public.users (user_id, username, password_hash, role) VALUES (1, 'Nick', 'hjkfdlasbhfdjska', 'ROLE_USER');

INSERT INTO public.cards (id, question, answer, tags, user_id) VALUES (1, 'What is 1+1', '2', 'Math', 1);
INSERT INTO public.cards (id, question, answer, tags, user_id) VALUES (2, 'What is 1-1', '0', 'Math', 1);
INSERT INTO public.cards (id, question, answer, tags, user_id) VALUES (3, 'water', 'H2O', 'Science', 1);

COMMIT TRANSACTION;