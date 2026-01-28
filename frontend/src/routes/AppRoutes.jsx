import { Routes, Route} from "react-router-dom";
import Home from "../pages/Home";
import MovieDetails from "../pages/MovieDetails";


const AppRoutes = () => {
    return (
        <Routes>
            <Route path = "/" element = {<Home />} />
            <Route path = "/movies/:movieId" element = {<MovieDetails />} />
        </Routes>
    );
};

export default AppRoutes;