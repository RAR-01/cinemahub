import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getMovieById } from "../api/movieApi";

function MovieDetails() {
  const { movieId } = useParams();
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const response = await getMovieById(movieId);
        setMovie(response.data);
      } catch (error) {
        console.error("Error fetching movie details", error);
      }
    };

    fetchMovie();
  }, [movieId]);

  if (!movie) {
    return <h2>Loading...</h2>;
  }

  return(
    <div>
      <h1>{movie.title}</h1>
      <p><strong>Genre:</strong> {movie.genre}</p>
      <p><strong>Rating:</strong> {movie.rating}</p>
      <p><strong>Language:</strong> {movie.langguage}</p>
      <p><strong>Duration:</strong> {movie.duration} mins</p>
      <p><strong>Description:</strong> {movie.description}</p>
    </div>
  );
}

export default MovieDetails;