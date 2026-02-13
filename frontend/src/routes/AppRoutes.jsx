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

            {/* Default route → Login */}
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            {/* App routes (after login) */}
            <Route path="/movies" element={<MovieList />} />
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
