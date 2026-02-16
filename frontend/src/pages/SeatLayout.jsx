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

    getSeatsByScreen(screenId).then(res => setSeats(res.data));
  }, [screenId, showId, navigate]);

  const toggleSeat = (seat) => {
    if (seat.seatStatus !== "AVAILABLE") return;

    setSelected(prev =>
      prev.includes(seat.id)
        ? prev.filter(id => id !== seat.id)
        : [...prev, seat.id]
    );
  };

  const lockAndCreateBooking = async () => {
    try {
      if (selected.length === 0) {
        alert("Please select at least one seat");
        return;
      }

      if (!showId) {
        alert("Show ID missing.");
        return;
      }

      await lockSeats(selected);

      const res = await createBooking(showId, selected);

      navigate(`/booking/${res.data.id}`, {
        state: res.data
      });

    } catch (err) {
      alert(
        err.response?.data?.message ||
        "Seat already booked or lock expired"
      );
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Select Seats</h2>

      <div className="row justify-content-center">
        <div className="col-md-8">
          <div
            className="d-grid gap-2"
            style={{ gridTemplateColumns: "repeat(8, 1fr)" }}
          >
            {seats.map(seat => {

              let btnClass = "btn ";

              if (seat.seatStatus === "BOOKED") {
                btnClass += "btn-secondary";
              } else if (selected.includes(seat.id)) {
                btnClass += "btn-warning";
              } else {
                btnClass += "btn-success";
              }

              return (
                <button
                  key={seat.id}
                  disabled={seat.seatStatus !== "AVAILABLE"}
                  onClick={() => toggleSeat(seat)}
                  className={btnClass}
                >
                  {seat.seatNumber}
                </button>
              );
            })}
          </div>
        </div>
      </div>

      <div className="mt-4 text-center">
        <span className="badge bg-success me-2">Available</span>
        <span className="badge bg-warning text-dark me-2">Selected</span>
        <span className="badge bg-secondary">Booked</span>
      </div>

      {selected.length > 0 && (
        <div className="text-center mt-4">
          <button
            className="btn btn-primary px-4"
            onClick={lockAndCreateBooking}
          >
            Proceed to Booking
          </button>
        </div>
      )}
    </div>
  );
}

export default SeatLayout;
