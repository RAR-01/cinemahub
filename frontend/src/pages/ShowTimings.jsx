import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getShowsByMovie } from "../api/showApi";

function ShowTimings() {
  const { movieId, theatreId } = useParams();
  const navigate = useNavigate();
  const [shows, setShows] = useState([]);

  useEffect(() => {
    getShowsByMovie(movieId).then(res => {
      setShows(
        res.data.filter(
          show => show.theatreId === Number(theatreId)
        )
      );
    });
  }, [movieId, theatreId]);

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Show Timings</h2>

      {shows.length === 0 ? (
        <div className="alert alert-warning text-center">
          No shows available
        </div>
      ) : (
        <div className="d-flex flex-wrap justify-content-center gap-3">
          {shows.map(show => (
            <button
              key={show.id}
              className="btn btn-outline-primary px-4 py-2"
              onClick={() =>
                navigate(`/screens/${show.screenId}/seats`)
              }
            >
              {new Date(show.startTime).toLocaleTimeString([], {
                hour: "2-digit",
                minute: "2-digit",
              })}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}

export default ShowTimings;
