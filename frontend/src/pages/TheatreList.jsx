import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "../api/axios";

function TheatreList() {
  const { movieId } = useParams();
  const navigate = useNavigate();
  const [theatres, setTheatres] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`/theatres/movies/${movieId}`)
      .then((res) => {
        setTheatres(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, [movieId]);

  if (loading) {
    return (
      <div className="container py-5 text-center">
        <div
          className="spinner-border text-primary"
          style={{ width: "3rem", height: "3rem" }}
        />
        <p className="mt-4 fs-5 text-muted">Loading theatres...</p>
      </div>
    );
  }

  return (
    <div className="container py-5">

      {/* Header */}
      <div className="text-center mb-5">
        <h2 className="fw-bold">Select Theatre</h2>
        <p className="text-muted">Choose your preferred cinema</p>
      </div>

      {theatres.length === 0 && (
        <div className="alert alert-warning text-center shadow-sm">
          No theatres available
        </div>
      )}

      <div className="row g-4">
        {theatres.map((theatre) => (
          <div key={theatre.id} className="col-12">
            <div
              className="card border-0 shadow-sm h-100"
              style={{
                cursor: "pointer",
                transition: "transform 0.2s ease, box-shadow 0.2s ease",
              }}
              onClick={() =>
                navigate(
                  `/movies/${movieId}/theatres/${theatre.id}/shows`
                )
              }
              onMouseEnter={(e) => {
                e.currentTarget.style.transform = "translateY(-4px)";
                e.currentTarget.style.boxShadow =
                  "0 8px 20px rgba(0,0,0,0.12)";
              }}
              onMouseLeave={(e) => {
                e.currentTarget.style.transform = "translateY(0)";
                e.currentTarget.style.boxShadow = "";
              }}
            >
              <div className="card-body d-flex justify-content-between align-items-center">

                <div>
                  <h5 className="fw-semibold mb-1">
                    {theatre.name}
                  </h5>
                  <p className="mb-0 text-muted">
                    📍 {theatre.city}
                  </p>
                </div>

                <div>
                  <span className="text-primary fw-semibold">
                    View Shows →
                  </span>
                </div>

              </div>
            </div>
          </div>
        ))}
      </div>

    </div>
  );
}

export default TheatreList;
