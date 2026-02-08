// 
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
    <div>
      <h2>Show Timings</h2>

      {shows.map(show => (
        <button
          key={show.id}
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
  );
}

export default ShowTimings;
