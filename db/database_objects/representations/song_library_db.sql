create table musician_bands (
    id serial primary key,
    title varchar(255) not null,
    country varchar(50),
    genre varchar(50),
    founding_year integer
);

create table albums (
    id serial primary key,
    name VARCHAR(255) NOT NULL,
    release_year integer,
    release_country varchar(255),
    label varchar(255),
    musician_bands_id integer references musician_bands (id)
);

create table songs (
    id serial primary key,
    name VARCHAR(255) NOT NULL,
    duration_in_seconds integer,
    albums_id integer references albums (id)
);

insert into musician_bands (title, country, genre, founding_year) values
    ('The Beatles', 'UK', 'Rock', 1960),
    ('Queen', 'UK', 'Rock', 1970),
    ('Metallica', 'USA', 'Heavy Metal', 1981),
    ('Led Zeppelin', 'UK', 'Hard Rock', 1968);

insert into albums (name, release_year, release_country, label, musician_bands_id) values
       ('Abbey Road', 1969, 'UK', 'Apple Records', 1),
       ('A Night at the Opera', 1975, 'UK', 'EMI Records', 2),
       ('Metallica', 1991, 'USA', 'Elektra', 3),
       ('Led Zeppelin IV', 1971, 'UK', 'Atlantic', 4),
       ('Sgt. Peppers Lonely Hearts Club Band', 1967, 'UK', 'Parlophone', 1),
       ('The Game', 1980, 'UK', 'EMI Records', 2),
       ('News of the World', 1977, 'UK', 'Parlophone', 2),
       ('…And Justice for All', 1988, 'USA', 'Elektra', 3),
       ('ReLoad', 1997, 'USA', 'Elektra', 3),
       ('Ride the Lightning', 1984, 'USA', 'Elektra', 3),
       ('Led Zeppelin III', 1970, 'UK', 'Atlantic', 4),
       ('Sheer Heart Attack', 1974, 'UK', 'Parlophone', 2);

insert into songs (name, duration_in_seconds, albums_id) values
    ('Come Together', 259, 1),
    ('You Never Give Me Your Money', 243, 1),
    ('Here Comes the Sun', 185, 1),
    ('Something', 182, 1),
    ('Bohemian Rhapsody', 355, 2),
    ('Love of My Life', 219, 2),
    ('Enter Sandman', 332, 3),
    ('Nothing Else Matters', 388, 3),
    ('Wherever I May Roam', 404, 3),
    ('The Unforgiven', 387, 3),
    ('Stairway to Heaven', 482, 4),
    ('Black Dog', 294, 4),
    ('Lovely Rita', 162, 5),
    ('A Day in the Life', 337, 5),
    ('Lucy in the Sky with Diamonds', 206, 5),
    ('Another One Bites the Dust', 215, 6),
    ('Crazy Little Thing Called Love', 182, 6),
    ('The Show Must Go On', 253, 6),
    ('We Are the Champions', 179, 7),
    ('We Will Rock You', 122, 7),
    ('One', 448, 8),
    ('Blackened', 402, 8),
    ('The Unforgiven II', 397, 9),
    ('The Memory Remains', 279, 9),
    ('Fade to Black', 418, 10),
    ('Ride the Lightning', 397, 10),
    ('Immigrant Song', 143, 11),
    ('Since I’ve Been Loving You', 444, 11),
    ('Now I’m Here', 250, 12),
    ('Killer Queen', 181, 12);

create view albums_older_than_10_years_and_duration
as
select mb.title as Группа,
    a.name as Альбом,
    sum(s.duration_in_seconds) as "Общая продолжительность"
from musician_bands as mb
inner join albums a on mb.id = a.musician_bands_id
inner join songs s on a.id = s.albums_id
where a.release_year > (
            select release_year + 10
			from albums
			where name ilike 'Abbey Road'
                        )
group by mb.title, a.name
