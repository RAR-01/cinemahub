import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getSeatsByScreen, lockSeats } from "../api/seatApi";

function SeatLayout() {
  const { screenId } = useParams();
  const [seats, setSeats] = useState([]);
  const [selected, setSelected] = useState([]);

  useEffect(() => {
    getSeatsByScreen(screenId).then(res => setSeats(res.data));
  }, [screenId]);

  const toggleSeat = (seat) => {
    if (seat.seatStatus !== "AVAILABLE") return;

    setSelected(prev =>
      prev.includes(seat.id)
        ? prev.filter(id => id !== seat.id)
        : [...prev, seat.id]
    );
  };

  const lockSelectedSeats = () => {
    lockSeats(selected)
      .then(() => {
        alert("Seats locked successfully");
      })
      .catch(err => {
        alert(
          err.response?.data?.message ||
          "Seat already booked or locked"
        );
      });
  };
  return (
    <div>
      <h2>Select Seats</h2>

      <div style={{
        display: "grid",
        gridTemplateColumns: "repeat(8, 1fr)",
        gap: "8px"
      }}>
        {seats.map(seat => (
          <div
            key={seat.id}
            onClick={() => toggleSeat(seat)}
            style={{
              padding: "10px",
              textAlign: "center",
              cursor: seat.seatStatus === "AVAILABLE" ? "pointer" : "not-allowed",
              background:
                seat.seatStatus === "BOOKED"
                  ? "#ccc"
                  : selected.includes(seat.id)
                    ? "#ffa500"
                    : "#90ee90"
            }}
          >
            {seat.seatNumber}
          </div>
        ))}
      </div>

      {selected.length > 0 && (
        <button onClick={lockSelectedSeats}>
          Lock Seats
        </button>
      )}
    </div>
  );
}

export default SeatLayout;
