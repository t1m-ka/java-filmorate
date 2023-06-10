INSERT INTO app_user (EMAIL , LOGIN, NAME, BIRTHDAY) VALUES ('user1@mail.ru', 'user1', 'George', '2010-10-10');
INSERT INTO app_user (EMAIL , LOGIN, NAME, BIRTHDAY) VALUES ('filmlover@mail.ru', 'filmlover', 'valera', '2011-11-11');
INSERT INTO app_user (EMAIL , LOGIN, NAME, BIRTHDAY) VALUES ('darkmaster@mail.ru', 'darkmaster', 'dmn666', '2012-12-12');
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
VALUES (SELECT id FROM APP_USER WHERE login='user1',SELECT id FROM FILM WHERE name='Форрест Гамп');
INSERT INTO LIKES (user_id, film_id)
VALUES (SELECT id FROM APP_USER WHERE login='filmlover',SELECT id FROM FILM WHERE name='Форрест Гамп');
INSERT INTO LIKES (user_id, film_id)
VALUES (SELECT id FROM APP_USER WHERE login='darkmaster',SELECT id FROM FILM WHERE name='Зеленая миля');
INSERT INTO LIKES (user_id, film_id)
VALUES (SELECT id FROM APP_USER WHERE login='darkmaster',SELECT id FROM FILM WHERE name='Побег из Шоушенка');

INSERT INTO friendship (follower_user_id, followed_user_id)
VALUES (1, 2);
INSERT INTO friendship (follower_user_id, followed_user_id)
VALUES (1, 3);
INSERT INTO friendship (follower_user_id, followed_user_id)
VALUES (2, 3);