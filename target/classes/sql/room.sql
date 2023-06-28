create table rooms(
 id serial primary key,
 name varchar(32),
 type varchar(16),
 capacity int
);

INSERT INTO rooms(room_id, name, type, capacity)
VALUES (1, 'my_taxi', 'conference', 1),
       (2, 'workly', 'team', 5),
       (3, 'express_24', 'focus', 15),
       (4, 'super_dipatch', 'conference', 19),
       (5, 'meeting', 'focus', 12),
       (6, 'impact', 'team', 10),
       (7, 'room', 'conference', 8),
       (8, 'lesson', 'team', 14);

create table room_dates(
    id serial primary key,
    start timestamp,
    end_ timestamp,
    active bool default true,
    room_id int references rooms(id)
);

INSERT INTO room_dates(book_id, start, end_, room_id, active)
VALUES (1, '2023-06-11 09:00:00', '2023-06-11 18:00:00', 1, true);\