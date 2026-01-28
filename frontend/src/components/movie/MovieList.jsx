import { useEffect, useState } from "react";
import MovieCard from "./MovieCard";
import { getAllMovies } from "../../services/movieService";

const MovieList = () => {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        getAllMovies()
            .then(setMovies)
            .catch(err => console.error(err));
    }, []);

    return (
        <div style = {{ display: "flex", gap: "20px", flexWrap: "wrap"}}>
            {movies.map(movie => (
                <MovieCard key = {movie.id} movie = {movie} />
            ))}
        </div>
    );
};

export default MovieList;