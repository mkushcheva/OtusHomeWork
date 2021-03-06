--liquibase formatted sql

--changeset mkushcheva:2021-10-28-fill-author
insert into author (author_id, author_name)
values (1, 'Пушкин А.С.'),
       (2, 'Волков А.М.'),
       (3, 'Носов Н.Н.')

--changeset mkushcheva:2021-10-28-fill-genre
insert into genre (genre_id, genre_name)
values (1, 'Сказка'),
       (2, 'Роман'),
       (3, 'Стихотворение')

--changeset mkushcheva:2021-10-28-fill-book
insert into book (book_id, book_title, author_id, genre_id, balance)
values (1, 'Волшебник изумрудного города', 2, 1, 10),
       (2, 'Сказка о рыбаке и рыбке', 1, 1, 10),
       (3, 'Сказка о золотом петушке', 1, 1, 20),
       (4, 'Скитания', 2, 2, 30),
       (5, 'Зодчие', 2, 2, 30),
       (6, 'Незнайка на луне', 3, 2, 25),
       (7, 'Няне', 1, 3, 100),
       (8, 'Мечты', 2, 3, 101),
       (9, 'Разведчики', 2, 3, 14)

--changeset mkushcheva:2022-02-10-fill-user
insert into user (user_id, user_login, user_password, user_role)
values (1, 'admin', '$2y$12$LWdOCN1HY2ZmiF4MGRRBxOwoHepgHQJ00fsjd6kVPGDsYS6D02ok6', 'ADMIN'),
    (2, 'mkushcheva', '12345678', 'ADMIN'),
    (3, 'reader', '$2y$12$a3DM//RQ.TTtiRxiOd8nEO9AiTnIO6aeoYACvzIB7whFfeowfDpV6', 'READER')