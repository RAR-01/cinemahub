import { Routes, Route } from "react-router-dom";

import Login from "../pages/Login";
import Register from "../pages/Register";

import MovieDetails from "../pages/MovieDetails";
import MovieList from "../pages/MovieList";
import TheatreList from "../pages/TheatreList";
import ShowTimings from "../pages/ShowTimings";
import SeatLayout from "../pages/SeatLayout";
import BookingSummary from "../pages/BookingSummary";
import PaymentPage from "../pages/PaymentPage";
import MyBookings from "../pages/MyBookings";

const AppRoutes = () => {
  return (
    <Routes>

      {/* Public routes */}
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      {/* Home after login */}
      <Route path="/" element={<MovieList />} />

      {/* Movie flow */}
      <Route path="/movies/:movieId" element={<MovieDetails />} />
      <Route path="/movies/:movieId/theatres" element={<TheatreList />} />
      <Route
        path="/movies/:movieId/theatres/:theatreId/shows"
        element={<ShowTimings />}
      />

      {/* Seat selection */}
      <Route path="/screens/:screenId/seats" element={<SeatLayout />} />

      {/* Booking summary */}
      <Route path="/booking/:bookingId" element={<BookingSummary />} />

      {/* Payment page */}
      <Route path="/payment/:bookingId" element={<PaymentPage />} />

      {/* My bookings */}
      <Route path="/my-bookings" element={<MyBookings />} />

    </Routes>
  );
};

export default AppRoutes;
