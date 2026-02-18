import { useEffect, useState } from "react";
import MovieCard from "../components/movie/MovieCard";
import { getAllMovies } from "../api/movieApi";

function MovieList() {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getAllMovies()
      .then((res) => {
        setMovies(res.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to load movies");
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="container py-5 text-center">
        <div className="spinner-border text-primary" style={{ width: "3rem", height: "3rem" }} />
        <p className="mt-4 fs-5 text-muted">Loading movies...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container py-5">
        <div className="alert alert-danger text-center shadow-sm">
          {error}
        </div>
      </div>
    );
  }

  return (
    <div className="container py-5">
      
      {/* Page Header */}
      <div className="text-center mb-5">
        <h2 className="fw-bold display-6">Now Showing</h2>
        <p className="text-muted">Book your favorite movie tickets instantly</p>
      </div>

      {/* Movie Grid */}
      <div className="row g-4">
        {movies.map((movie) => (
          <div
            key={movie.id}
            className="col-12 col-sm-6 col-md-4 col-lg-3"
          >
            <MovieCard movie={movie} />
          </div>
        ))}
      </div>

    </div>
  );
}

export default MovieList;
