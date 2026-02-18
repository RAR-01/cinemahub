import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getMovieById } from "../api/movieApi";

const TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/w500";
const FALLBACK_POSTER =
  "https://via.placeholder.com/500x750?text=No+Poster";

function MovieDetails() {
  const { movieId } = useParams();
  const navigate = useNavigate();
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    getMovieById(movieId)
      .then((res) => setMovie(res.data))
      .catch((err) => console.error(err));
  }, [movieId]);

  if (!movie) {
    return (
      <div className="container py-5 text-center">
        <div
          className="spinner-border text-primary"
          style={{ width: "3rem", height: "3rem" }}
        />
        <p className="mt-4 fs-5 text-muted">Loading movie details...</p>
      </div>
    );
  }

  const posterUrl = movie.posterPath
    ? `${TMDB_IMAGE_BASE}${movie.posterPath}`
    : FALLBACK_POSTER;

  return (
    <div className="container py-5">
      <div className="row align-items-start g-5">

        {/* Poster */}
        <div className="col-12 col-md-4 text-center">
          <img
            src={posterUrl}
            alt={movie.title}
            className="img-fluid rounded-3 shadow"
            style={{ maxHeight: "520px", objectFit: "cover" }}
          />
        </div>

        {/* Details */}
        <div className="col-12 col-md-8">

          <h2 className="fw-bold mb-3">{movie.title}</h2>

          {/* Metadata badges */}
          <div className="mb-4 d-flex flex-wrap gap-2">
            <span className="badge bg-secondary">
              🎬 {movie.genre}
            </span>
            <span className="badge bg-warning text-dark">
              ⭐ {movie.rating}
            </span>
            <span className="badge bg-info text-dark">
              🌐 {movie.language}
            </span>
            <span className="badge bg-dark">
              ⏱ {movie.duration} mins
            </span>
          </div>

          {/* Description */}
          <p className="text-muted fs-6 lh-lg">
            {movie.description}
          </p>

          {/* CTA */}
          <div className="mt-4">
            <button
              className="btn btn-primary btn-lg px-4"
              onClick={() => navigate(`/movies/${movieId}/theatres`)}
            >
              Book Tickets
            </button>
          </div>

        </div>
      </div>
    </div>
  );
}

export default MovieDetails;
