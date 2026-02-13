import { useEffect, useState } from "react";
import MovieCard from "../components/movie/MovieCard";
import { getAllMovies } from "../api/movieApi";

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
    }, []);

    if (loading) {
        return (
            <div className="container mt-5 text-center">
                <div className="spinner-border text-primary"></div>
                <p className="mt-3">Loading movies...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container mt-5">
                <div className="alert alert-danger text-center">
                    {error}
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-5">
            <h2 className="mb-4 text-center">Now Showing</h2>

            <div className="row">
                {movies.map(movie => (
                    <div key={movie.id} className="col-md-3 col-sm-6 mb-4">
                        <MovieCard movie={movie} />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default MovieList;
