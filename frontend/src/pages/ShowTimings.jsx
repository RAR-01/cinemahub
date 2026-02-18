import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getShowsByMovie } from "../api/showApi";

function ShowTimings() {
  const { movieId, theatreId } = useParams();
  const navigate = useNavigate();
  const [shows, setShows] = useState([]);

  useEffect(() => {
    getShowsByMovie(movieId).then((res) => {
      setShows(
        res.data.filter(
          (show) => show.theatreId === Number(theatreId)
        )
      );
    });
  }, [movieId, theatreId]);

  return (
    <div className="container py-5">

      {/* Header */}
      <div className="text-center mb-5">
        <h2 className="fw-bold">Show Timings</h2>
        <p className="text-muted">
          Select your preferred show time
        </p>
      </div>

      {shows.length === 0 ? (
        <div className="alert alert-warning text-center shadow-sm">
          No shows available
        </div>
      ) : (
        <div className="card border-0 shadow-sm p-4">
          <div className="d-flex flex-wrap justify-content-center gap-3">

            {shows.map((show) => (
              <button
                key={show.id}
                className="btn btn-outline-primary px-4 py-2 fw-semibold"
                style={{
                  minWidth: "120px",
                  transition: "all 0.2s ease",
                }}
                onClick={() =>
                  navigate(`/screens/${show.screenId}/seats`, {
                    state: { showId: show.id },
                  })
                }
              >
                {new Date(show.startTime).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
              </button>
            ))}

          </div>
        </div>
      )}

    </div>
  );
}

export default ShowTimings;
