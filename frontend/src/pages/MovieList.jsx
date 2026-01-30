import { useEffect, useState } from 'react';
import api from "../api/axios";
import MovieCard from "../components/movie/MovieCard";

function MovieList() {
    
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    
    useEffect(() => {
        api.get("/movies")
            .then(res => {
                setMovies(res.data);
                setLoading(false);
            })
            .catch(err => {
                setError("Failed to load movies");
                setLoading(false);
            });
    },[]);

    if (loading) return <p>Loading movies...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <h1>Now Showing</h1>
            {movies.map(movie => (
                <MovieCard key={movie.id} movie ={movie} />
            ))}
        </div>
    );
}

export default MovieList;