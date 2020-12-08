BEGIN TRANSACTION;

INSERT INTO public.users (user_id, username, password_hash, role) VALUES (1, 'Nick', 'hjkfdlasbhfdjska', 'ROLE_USER');

INSERT INTO public.cards (question, answer, tags, user_id) VALUES ('What is 1+1', '2', 'Math', 1);
INSERT INTO public.cards (question, answer, tags, user_id) VALUES ('What is 1-1', '0', 'Math', 1);
INSERT INTO public.cards (question, answer, tags, user_id) VALUES ('water', 'H2O', 'Science', 1);

COMMIT TRANSACTION;