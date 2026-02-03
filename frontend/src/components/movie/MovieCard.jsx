import { useNavigate } from "react-router-dom";


const MovieCard = ({ movie }) => {
    const navigate = useNavigate();

    return (
        <div
            onClick={() => navigate(`/movies/${movie.id}`)}
            style={{
                border: "1px solid #34a5c8",
                padding: "10px",
                width: "200px",
                cursor: "pointer"
            }}
        >
            <h3>{movie.title}</h3>
            <p><b>Genre:</b> {movie.genre}</p>
            <p><b>Rating:</b> ⭐ {movie.rating}</p>
        </div>
    );
};

export default MovieCard;