import { useNavigate } from "react-router-dom";

const TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/w300";
const FALLBACK_POSTER =
    "https://via.placeholder.com/300x450?text=No+Poster";

const MovieCard = ({ movie }) => {
    const navigate = useNavigate();

    const posterUrl = movie.posterPath
        ? `${TMDB_IMAGE_BASE}${movie.posterPath}`
        : FALLBACK_POSTER;

    return (
        <div
            className="card h-100 shadow-sm"
            style={{ cursor: "pointer" }}
            onClick={() => navigate(`/movies/${movie.id}`)}
        >
            <img
                src={posterUrl}
                className="card-img-top"
                alt={movie.title}
                style={{ height: "300px", objectFit: "cover" }}
            />

            <div className="card-body">
                <h5 className="card-title">{movie.title}</h5>

                <p className="card-text mb-1">
                    <strong>Genre:</strong> {movie.genre}
                </p>

                <p className="card-text">
                    <strong>Rating:</strong> ⭐ {movie.rating}
                </p>
            </div>
        </div>
    );
};

export default MovieCard;
