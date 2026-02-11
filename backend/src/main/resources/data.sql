-- ================================
-- THEATRES
-- ================================
INSERT INTO theatres (id, name, city)
VALUES
(1, 'PVR Phoenix Mall', 'Bangalore'),
(2, 'INOX Central', 'Bangalore');

-- ================================
-- MOVIES
-- ================================
INSERT INTO movies (
  id,
  title,
  genre,
  rating,
  description,
  duration,
  language,
  tmdb_id,
  poster_path
)
VALUES
(
  1,
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
  2,
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
-- THEATRE_MOVIES (Many-to-Many)
-- ================================
INSERT INTO theatre_movies (theatre_id, movie_id)
VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

-- ================================
-- SCREENS
-- ================================
INSERT INTO screens (id, name, theatre_id)
VALUES
(1, 'Screen 1', 1),
(2, 'Screen 2', 1),
(3, 'Screen 1', 2),
(4, 'Screen 2', 2);

-- ================================
-- SHOWS
-- ================================
INSERT INTO shows (id, movie_id, theatre_id, screen_id, start_time, end_time)
VALUES
(1, 1, 1, 1, '2026-01-21 18:30:00', '2026-01-21 21:20:00'),
(2, 2, 1, 2, '2026-01-21 22:00:00', '2026-01-22 00:30:00'),
(3, 1, 2, 3, '2026-01-21 19:00:00', '2026-01-21 21:50:00'),
(4, 2, 2, 4, '2026-01-21 21:30:00', '2026-01-22 00:00:00');

-- ================================
-- SEATS (Screen 1 - Theatre 1)
-- ================================
INSERT INTO seats
(id, seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
(1, 'A1', 'A', 1, 'REGULAR',  'AVAILABLE', 1, 150),
(2, 'A2', 'A', 2, 'REGULAR',  'AVAILABLE', 1, 150),
(3, 'A3', 'A', 3, 'PREMIUM',  'AVAILABLE', 1, 250),
(4, 'A4', 'A', 4, 'PREMIUM',  'AVAILABLE', 1, 250),
(5, 'A5', 'A', 5, 'RECLINER', 'AVAILABLE', 1, 400);

-- ================================
-- SEATS (Screen 2 - Theatre 1)
-- ================================
INSERT INTO seats
(id, seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
(6, 'B1', 'B', 1, 'REGULAR',  'AVAILABLE', 2, 150),
(7, 'B2', 'B', 2, 'REGULAR',  'AVAILABLE', 2, 150),
(8, 'B3', 'B', 3, 'PREMIUM',  'AVAILABLE', 2, 250),
(9, 'B4', 'B', 4, 'PREMIUM',  'AVAILABLE', 2, 250),
(10,'B5', 'B', 5, 'RECLINER', 'AVAILABLE', 2, 400);

-- ================================
-- SEATS (Screen 1 - Theatre 2)
-- ================================
INSERT INTO seats
(id, seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
(11,'C1', 'C', 1, 'REGULAR',  'AVAILABLE', 3, 150),
(12,'C2', 'C', 2, 'REGULAR',  'AVAILABLE', 3, 150),
(13,'C3', 'C', 3, 'PREMIUM',  'AVAILABLE', 3, 250),
(14,'C4', 'C', 4, 'PREMIUM',  'AVAILABLE', 3, 250),
(15,'C5', 'C', 5, 'RECLINER', 'AVAILABLE', 3, 400);

-- ================================
-- SEATS (Screen 2 - Theatre 2)
-- ================================
INSERT INTO seats
(id, seat_number, row_label, column_number, seat_type, seat_status, screen_id, price)
VALUES
(16,'D1', 'D', 1, 'REGULAR',  'AVAILABLE', 4, 150),
(17,'D2', 'D', 2, 'REGULAR',  'AVAILABLE', 4, 150),
(18,'D3', 'D', 3, 'PREMIUM',  'AVAILABLE', 4, 250),
(19,'D4', 'D', 4, 'PREMIUM',  'AVAILABLE', 4, 250),
(20,'D5', 'D', 5, 'RECLINER', 'AVAILABLE', 4, 400);


-- ================================
-- USERS
-- ================================
INSERT INTO users (id, name, email, password, created_at)
VALUES
(1, 'Rishi Rai', 'rishi@gmail.com',
'$2a$10$Dow1X0X8cYpXwS1Xx2YQ7e8x8lOe7kQ6VJ5sG9Lw2fXJ7YkN8uF2K',
NOW());

-- ================================
-- BOOKINGS
-- ================================
INSERT INTO bookings (user_id, show_id, status, total_amount, created_at)
VALUES (1, 1, 'CONFIRMED', 500.0, NOW());

-- ================================
-- BOOKING SEATS
-- ================================
INSERT INTO booking_seats (booking_id, seat_id)
VALUES (1, 1), (1, 2);

