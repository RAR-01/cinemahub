-- ================================
-- THEATRES
-- ================================
INSERT IGNORE INTO theatres (name, city)
VALUES
('PVR Phoenix Mall', 'Bangalore'),
('INOX Central', 'Bangalore');


-- ================================
-- MOVIES
-- ================================
INSERT IGNORE INTO movies
(title, genre, rating, description, duration, language, tmdb_id, poster_path)
VALUES
(
  'Interstellar',
  'Sci-Fi',
  8.6,
  'A team of explorers travel through a wormhole in space to ensure humanity’s survival.',
  169,
  'English',
  '157336',
  '/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg'
),
(
  'Inception',
  'Sci-Fi',
  8.8,
  'A skilled thief enters dreams to steal and manipulate secrets.',
  148,
  'English',
  '27205',
  '/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg'
);


-- ================================
-- THEATRE_MOVIES
-- ================================
INSERT IGNORE INTO theatre_movies (theatre_id, movie_id)
VALUES
(1,1),
(1,2),
(2,1),
(2,2);


-- ================================
-- SCREENS
-- ================================
INSERT IGNORE INTO screens (name, theatre_id)
VALUES
('Screen 1',1),
('Screen 2',1),
('Screen 1',2),
('Screen 2',2);


-- ================================
-- SHOWS
-- ================================
INSERT IGNORE INTO shows (movie_id, theatre_id, screen_id, start_time, end_time)
VALUES
(1,1,1,'2026-02-12 18:30:00','2026-02-12 21:20:00'),
(2,1,2,'2026-02-12 22:00:00','2026-02-13 00:30:00'),
(1,2,3,'2026-02-12 19:00:00','2026-02-12 21:50:00'),
(2,2,4,'2026-02-12 21:30:00','2026-02-13 00:00:00');


-- ================================
-- SEATS (Screen 1 - Theatre 1)
-- ================================
INSERT IGNORE INTO seats
(seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
('A1','A',1,'REGULAR','AVAILABLE',1,150),
('A2','A',2,'REGULAR','AVAILABLE',1,150),
('A3','A',3,'PREMIUM','AVAILABLE',1,250),
('A4','A',4,'PREMIUM','AVAILABLE',1,250),
('A5','A',5,'RECLINER','AVAILABLE',1,400);


-- ================================
-- SEATS (Screen 2 - Theatre 1)
-- ================================
INSERT IGNORE INTO seats
(seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
('B1','B',1,'REGULAR','AVAILABLE',2,150),
('B2','B',2,'REGULAR','AVAILABLE',2,150),
('B3','B',3,'PREMIUM','AVAILABLE',2,250),
('B4','B',4,'PREMIUM','AVAILABLE',2,250),
('B5','B',5,'RECLINER','AVAILABLE',2,400);


-- ================================
-- SEATS (Screen 1 - Theatre 2)
-- ================================
INSERT IGNORE INTO seats
(seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
('C1','C',1,'REGULAR','AVAILABLE',3,150),
('C2','C',2,'REGULAR','AVAILABLE',3,150),
('C3','C',3,'PREMIUM','AVAILABLE',3,250),
('C4','C',4,'PREMIUM','AVAILABLE',3,250),
('C5','C',5,'RECLINER','AVAILABLE',3,400);


-- ================================
-- SEATS (Screen 2 - Theatre 2)
-- ================================
INSERT IGNORE INTO seats
(seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
('D1','D',1,'REGULAR','AVAILABLE',4,150),
('D2','D',2,'REGULAR','AVAILABLE',4,150),
('D3','D',3,'PREMIUM','AVAILABLE',4,250),
('D4','D',4,'PREMIUM','AVAILABLE',4,250),
('D5','D',5,'RECLINER','AVAILABLE',4,400);
