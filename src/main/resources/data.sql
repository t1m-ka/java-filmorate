/*INSERT INTO mpa (name) VALUES ('G');
INSERT INTO mpa (name) VALUES ('PG');
INSERT INTO mpa (name) VALUES ('PG-13');
INSERT INTO mpa (name) VALUES ('R');
INSERT INTO mpa (name) VALUES ('NC-17');

INSERT INTO genre (name) VALUES ('Комедия');
INSERT INTO genre (name) VALUES ('Драма');
INSERT INTO genre (name) VALUES ('Мультфильм');
INSERT INTO genre (name) VALUES ('Триллер');
INSERT INTO genre (name) VALUES ('Документальный');
INSERT INTO genre (name) VALUES ('Боевик');*/

/*INSERT INTO app_user (EMAIL , LOGIN, NAME, BIRTHDAY) VALUES ('user1@mail.ru', 'user1', 'George', '2010-10-10');
INSERT INTO FILM  (name, description, release_date, duration, mpa_id)
VALUES ('Зеленая миля', 'Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни', '1999-12-06', 189, 4);
INSERT INTO FILM (name, description, release_date, duration, mpa_id)
VALUES ('Побег из Шоушенка', 'Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки.', '1994-09-10', 142, 4);
INSERT INTO FILM (name, description, release_date, duration, mpa_id)
VALUES ('Форрест Гамп', 'Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни.', '1994-06-23', 142, 3);

INSERT INTO FILM_GENRE (film_id, genre_id)
VALUES (SELECT id FROM FILM WHERE name='Зеленая миля',SELECT id FROM GENRE WHERE name='Драма');
INSERT INTO FILM_GENRE (film_id, genre_id)
VALUES (SELECT id FROM FILM WHERE name='Побег из Шоушенка',SELECT id FROM GENRE WHERE name='Драма');
INSERT INTO FILM_GENRE (film_id, genre_id)
VALUES (SELECT id FROM FILM WHERE name='Форрест Гамп',SELECT id FROM GENRE WHERE name='Драма');
INSERT INTO FILM_GENRE (film_id, genre_id)
VALUES (SELECT id FROM FILM WHERE name='Форрест Гамп',SELECT id FROM GENRE WHERE name='Комедия');

INSERT INTO LIKES (user_id, film_id)
VALUES (SELECT id FROM APP_USER WHERE login='user1',SELECT id FROM FILM WHERE name='Побег из Шоушенка');
INSERT INTO LIKES (user_id, film_id)
VALUES (SELECT id FROM APP_USER WHERE login='user1',SELECT id FROM FILM WHERE name='Форрест Гамп');*/

