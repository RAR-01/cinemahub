import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "../api/axios";

const TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/w500";
const FALLBACK_POSTER =
    "https://via.placeholder.com/500x750?text=No+Poster";

const MovieDetails = () => {
    const { movieId } = useParams();
    const [movie, setMovie] = useState(null);

    useEffect(() => {
        axios.get(`/movies/${movieId}`)
            .then(res => setMovie(res.data));
    }, [movieId]);

    if (!movie) return <p>Loading...</p>;

    const posterUrl = movie.posterPath
        ? `${TMDB_IMAGE_BASE}${movie.posterPath}`
        : FALLBACK_POSTER;

    return (
        <div>
            <img
                src={posterUrl}
                alt={movie.title}
                style={{ width: "300px" }}
            />

            <h2>{movie.title}</h2>
            <p><b>Genre:</b> {movie.genre}</p>
            <p><b>Rating:</b> ⭐ {movie.rating}</p>
            <p><b>Duration:</b> {movie.duration} mins</p>
            <p><b>Language:</b> {movie.language}</p>
            <p>{movie.description}</p>

            <button>Book Tickets</button>
        </div>
    );
};

export default MovieDetails;
