import { useEffect, useState } from 'react';
import MovieCard from "../components/movie/MovieCard";
import { getAllMovies } from '../api/movieApi';

function MovieList() {
    
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    
    useEffect(() => {
        getAllMovies()
            .then(res => {
                setMovies(res.data);
                setLoading(false);
            })
            .catch(() => {
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