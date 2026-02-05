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
            onClick={() => navigate(`/movies/${movie.id}`)}
            style={{
                border: "1px solid #1c718b",
                padding: "10px",
                width: "200px",
                cursor: "pointer"
            }}
        >
            <img
                src={posterUrl}
                alt={movie.title}
                style={{
                    width: "100%",
                    height: "300px",
                    objectFit: "cover",
                    marginBottom: "8px"
                }}
            />

            <h3>{movie.title}</h3>
            <p><b>Genre:</b> {movie.genre}</p>
            <p><b>Rating:</b> ⭐ {movie.rating}</p>
        </div>
    );
};

export default MovieCard;
