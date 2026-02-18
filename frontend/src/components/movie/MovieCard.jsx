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
      className="card h-100 border-0 shadow-sm movie-card"
      style={{
        cursor: "pointer",
        transition: "transform 0.2s ease, box-shadow 0.2s ease",
      }}
      onClick={() => navigate(`/movies/${movie.id}`)}
      onMouseEnter={(e) => {
        e.currentTarget.style.transform = "translateY(-5px)";
        e.currentTarget.style.boxShadow = "0 8px 20px rgba(0,0,0,0.15)";
      }}
      onMouseLeave={(e) => {
        e.currentTarget.style.transform = "translateY(0)";
        e.currentTarget.style.boxShadow = "";
      }}
    >
      {/* Poster Container */}
      <div className="position-relative">
        <img
          src={posterUrl}
          className="card-img-top"
          alt={movie.title}
          style={{
            aspectRatio: "2/3",
            objectFit: "cover",
            borderTopLeftRadius: "0.5rem",
            borderTopRightRadius: "0.5rem",
          }}
        />

        {/* Rating Badge */}
        <span
          className="badge bg-warning text-dark position-absolute"
          style={{ top: "10px", right: "10px" }}
        >
          ⭐ {movie.rating}
        </span>
      </div>

      <div className="card-body d-flex flex-column">
        <h5 className="card-title fw-semibold mb-2 text-truncate">
          {movie.title}
        </h5>

        <p className="card-text text-muted mb-3 small">
          {movie.genre}
        </p>

        <div className="mt-auto">
          <button className="btn btn-primary w-100">
            View Shows
          </button>
        </div>
      </div>
    </div>
  );
};

export default MovieCard;
