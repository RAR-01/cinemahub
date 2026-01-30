import { Routes, Route} from "react-router-dom";
import Home from "../pages/Home";
import MovieDetails from "../pages/MovieDetails";
import MovieList from "../pages/MovieList";


const AppRoutes = () => {
    return (
        <Routes>
            <Route path = "/" element = {<Home />} />
            <Route path ="/movies" element={<MovieList />} />
            <Route path = "/movies/:movieId" element = {<MovieDetails />} />
        </Routes>
    );
};

export default AppRoutes;