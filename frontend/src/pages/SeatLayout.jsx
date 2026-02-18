import { useEffect, useState } from "react";
import { useParams, useLocation, useNavigate } from "react-router-dom";
import { getSeatsByScreen, lockSeats } from "../api/seatApi";
import { createBooking } from "../api/bookingApi";

function SeatLayout() {
  const { screenId } = useParams();
  const location = useLocation();
  const navigate = useNavigate();

  const showId = location.state?.showId;

  const [seats, setSeats] = useState([]);
  const [selected, setSelected] = useState([]);

  useEffect(() => {
    if (!showId) {
      alert("Invalid show selection. Please select show again.");
      navigate(-1);
      return;
    }

    getSeatsByScreen(screenId).then((res) =>
      setSeats(res.data)
    );
  }, [screenId, showId, navigate]);

  const toggleSeat = (seat) => {
    if (seat.seatStatus !== "AVAILABLE") return;

    setSelected((prev) =>
      prev.includes(seat.id)
        ? prev.filter((id) => id !== seat.id)
        : [...prev, seat.id]
    );
  };

  const lockAndCreateBooking = async () => {
    try {
      if (selected.length === 0) {
        alert("Please select at least one seat");
        return;
      }

      await lockSeats(selected);
      const res = await createBooking(showId, selected);

      navigate(`/booking/${res.data.id}`, {
        state: res.data,
      });
    } catch (err) {
      alert(
        err.response?.data?.message ||
          "Seat already booked or lock expired"
      );
    }
  };

  return (
    <div className="container py-5">

      {/* Header */}
      <div className="text-center mb-4">
        <h2 className="fw-bold">Select Seats</h2>
        <p className="text-muted">
          Choose your preferred seats
        </p>
      </div>

      {/* Screen Indicator */}
      <div className="text-center mb-4">
        <div
          className="mx-auto mb-2"
          style={{
            height: "8px",
            width: "60%",
            background:
              "linear-gradient(to right, #ccc, #eee, #ccc)",
            borderRadius: "50px",
          }}
        />
        <small className="text-muted">SCREEN THIS WAY</small>
      </div>

      {/* Seat Grid */}
      <div className="d-flex justify-content-center">
        <div
          className="d-grid"
          style={{
            gridTemplateColumns: "repeat(8, 40px)",
            gap: "10px",
          }}
        >
          {seats.map((seat) => {
            let bgColor = "#28a745"; // available (green)

            if (seat.seatStatus === "BOOKED") {
              bgColor = "#6c757d"; // grey
            } else if (selected.includes(seat.id)) {
              bgColor = "#ffc107"; // yellow
            }

            return (
              <button
                key={seat.id}
                disabled={seat.seatStatus !== "AVAILABLE"}
                onClick={() => toggleSeat(seat)}
                style={{
                  height: "40px",
                  width: "40px",
                  borderRadius: "6px",
                  border: "1px solid #ddd",
                  fontSize: "12px",
                  fontWeight: "600",
                  backgroundColor: bgColor,
                  color:
                    selected.includes(seat.id)
                      ? "#000"
                      : "#fff",
                  transition: "all 0.2s ease",
                }}
              >
                {seat.seatNumber}
              </button>
            );
          })}
        </div>
      </div>

      {/* Legend */}
      <div className="d-flex justify-content-center gap-4 mt-5">
        <div className="d-flex align-items-center gap-2">
          <div
            style={{
              height: "18px",
              width: "18px",
              backgroundColor: "#28a745",
              borderRadius: "4px",
            }}
          />
          <small>Available</small>
        </div>

        <div className="d-flex align-items-center gap-2">
          <div
            style={{
              height: "18px",
              width: "18px",
              backgroundColor: "#ffc107",
              borderRadius: "4px",
            }}
          />
          <small>Selected</small>
        </div>

        <div className="d-flex align-items-center gap-2">
          <div
            style={{
              height: "18px",
              width: "18px",
              backgroundColor: "#6c757d",
              borderRadius: "4px",
            }}
          />
          <small>Booked</small>
        </div>
      </div>

      {/* CTA */}
      {selected.length > 0 && (
        <div className="text-center mt-5">
          <button
            className="btn btn-primary btn-lg px-5"
            onClick={lockAndCreateBooking}
          >
            Proceed to Booking ({selected.length})
          </button>
        </div>
      )}
    </div>
  );
}

export default SeatLayout;
