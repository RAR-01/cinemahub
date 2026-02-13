import MovieList from "./MovieList";

function Home() {
  return (
    <div className="container mt-4">
      <div className="text-center mb-4">
        <h1 className="fw-bold">🎬 CinemaHub</h1>
        <p className="text-muted">
          Book your favorite movies easily and quickly.
        </p>
      </div>

      <MovieList />
    </div>
  );
}

export default Home;
