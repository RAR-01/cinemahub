import { Routes, Route} from "react-router-dom";
import Home from "../pages/Home";
import MovieDetails from "../pages/MovieDetails";
import MovieList from "../pages/MovieList";
import TheatreList from "../pages/TheatreList";
import ShowTimings from "../pages/ShowTimings";
const AppRoutes = () => {
    return (
        <Routes>
            <Route path = "/" element = {<Home />} />
            <Route path ="/movies" element={<MovieList />} />
            <Route path = "/movies/:movieId" element = {<MovieDetails />} />
            <Route path = "/movies/:movieId/theatres" element={<TheatreList />} />
            <Route path = "/movies/:movieId/theatres/:theatreId/shows" element={<ShowTimings />} />
            <Route path="/screens/:screenId/seats" element={<SeatLayout />}/>
        </Routes>
    );
};

export default AppRoutes;