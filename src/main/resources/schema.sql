CREATE SCHEMA IF NOT EXISTS PUBLIC;

DROP TABLE IF EXISTS public.film_genre;
DROP TABLE IF EXISTS public.likes;
DROP TABLE IF EXISTS public.friendship;
DROP TABLE IF EXISTS public.film;
DROP TABLE IF EXISTS public.genre;
DROP TABLE IF EXISTS public.mpa;
DROP TABLE IF EXISTS public.app_user;

CREATE TABLE IF NOT EXISTS public."app_user" (
id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
email VARCHAR(50) NOT NULL UNIQUE,
login VARCHAR(50) NOT NULL UNIQUE,
name VARCHAR(50),
birthday date NOT NULL
);

CREATE TABLE IF NOT EXISTS public.mpa (
id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS public.genre (
id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS public.film (
id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
name varchar(50) NOT NULL,
description varchar(200),
release_date date NOT NULL,
duration INTEGER NOT NULL,
mpa_id INTEGER REFERENCES mpa (id)
);

CREATE TABLE IF NOT EXISTS public.friendship (
follower_user_id INTEGER NOT NULL REFERENCES app_user (id),
followed_user_id INTEGER NOT NULL REFERENCES app_user (id),
CONSTRAINT friend_pr_key PRIMARY KEY (follower_user_id, followed_user_id)
);

CREATE TABLE IF NOT EXISTS public.likes (
user_id INTEGER NOT NULL REFERENCES app_user (id),
film_id INTEGER NOT NULL REFERENCES film (id),
CONSTRAINT likes_pr_key PRIMARY KEY (user_id, film_id)
);

CREATE TABLE IF NOT EXISTS public.film_genre (
film_id INTEGER NOT NULL REFERENCES film (id),
genre_id INTEGER NOT NULL REFERENCES genre (id),
CONSTRAINT fg_pr_key PRIMARY KEY (film_id, genre_id)
);