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
      .then(res => setMovie(res.data))
      .catch(err => console.error(err));
  }, [movieId]);

  if (!movie) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border text-primary"></div>
        <p className="mt-3">Loading...</p>
      </div>
    );
  }

  const posterUrl = movie.posterPath
    ? `${TMDB_IMAGE_BASE}${movie.posterPath}`
    : FALLBACK_POSTER;

  return (
    <div className="container mt-5">
      <div className="row">
        
        <div className="col-md-4 text-center">
          <img
            src={posterUrl}
            alt={movie.title}
            className="img-fluid rounded shadow"
          />
        </div>

        <div className="col-md-8">
          <h2 className="mb-3">{movie.title}</h2>

          <p><strong>Genre:</strong> {movie.genre}</p>
          <p><strong>Rating:</strong> ⭐ {movie.rating}</p>
          <p><strong>Language:</strong> {movie.language}</p>
          <p><strong>Duration:</strong> {movie.duration} mins</p>
          <p className="mt-3">{movie.description}</p>

          <button
            className="btn btn-primary mt-4"
            onClick={() => navigate(`/movies/${movieId}/theatres`)}
          >
            Book Tickets
          </button>
        </div>

      </div>
    </div>
  );
}

export default MovieDetails;
