import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getMovieById } from "../services/movieService";

function MovieDetails() {
  const { movieId } = useParams();
  const [movie, setMovie] = useParams(null);

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
    </div>
  );
}


export default MovieDetails;