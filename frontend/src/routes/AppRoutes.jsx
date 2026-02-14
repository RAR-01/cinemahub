import { Routes, Route } from "react-router-dom";

import Login from "../pages/Login";
import Register from "../pages/Register";

import MovieDetails from "../pages/MovieDetails";
import MovieList from "../pages/MovieList";
import TheatreList from "../pages/TheatreList";
import ShowTimings from "../pages/ShowTimings";
import SeatLayout from "../pages/SeatLayout";
import BookingSummary from "../pages/BookingSummary";
import MyBookings from "../pages/MyBookings";

const AppRoutes = () => {
    return (
        <Routes>

            {/* Public routes */}
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* Home page after login */}
            <Route path="/" element={<MovieList />} />

            {/* App routes */}
            <Route path="/movies/:movieId" element={<MovieDetails />} />
            <Route path="/movies/:movieId/theatres" element={<TheatreList />} />
            <Route path="/movies/:movieId/theatres/:theatreId/shows" element={<ShowTimings />} />
            <Route path="/screens/:screenId/seats" element={<SeatLayout />} />
            <Route path="/booking/:bookingId" element={<BookingSummary />} />
            <Route path="/my-bookings" element={<MyBookings />} />

        </Routes>
    );
};

export default AppRoutes;
