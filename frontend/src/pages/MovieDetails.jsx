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
    return <h2>Loading...</h2>;
  }

  const posterUrl = movie.posterPath
    ? `${TMDB_IMAGE_BASE}${movie.posterPath}`
    : FALLBACK_POSTER;

  return(
    <div>
      <h1>{movie.title}</h1>
      <p><strong>Genre:</strong> {movie.genre}</p>
      <p><strong>Rating:</strong> ⭐ {movie.rating}</p>
      <p><strong>Language:</strong> {movie.langguage}</p>
      <p><strong>Duration:</strong> {movie.duration} mins</p>
      <p><strong>Description:</strong> {movie.description}</p>

      <button 
        onClick={() => navigate(`/movies/${movieId}/theatres`)}>
          Book Tickets
        </button>
    </div>
  );
}

export default MovieDetails;