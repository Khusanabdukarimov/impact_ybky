create table rooms(
 id serial primary key,
 name varchar(32),
 type varchar(16),
 capacity int
);

INSERT INTO rooms(room_id, name, type, capacity)
VALUES (1, 'my_taxi', 0, 1),
       (2, 'workly', 1, 5),
       (3, 'express_24', 2, 15),
       (4, 'super_dipatch', 0, 19),
       (5, 'meeting', 2, 12),
       (6, 'impact', 1, 10),
       (7, 'room', 0, 8),
       (8, 'lesson', 1, 14);

create table room_dates(
    id serial primary key,
    start timestamp,
    end_ timestamp,
    active bool default true,
    room_id int references rooms(id)
);

INSERT INTO room_dates(book_id, start, end_, room_id, active)
VALUES (1, '2023-06-11 09:00:00', '2023-06-11 18:00:00', 1, true);\