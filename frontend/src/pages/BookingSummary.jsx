import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "../api/axios";

function BookingSummary() {
  const location = useLocation();
  const navigate = useNavigate();
  const { bookingId } = useParams();

  const [booking, setBooking] = useState(location.state || null);

  // If page refreshed → fetch booking from backend
  useEffect(() => {
    if (!booking && bookingId) {
      axios.get(`/bookings/${bookingId}`)
        .then(res => setBooking(res.data))
        .catch(() => navigate("/"));
    }
  }, [booking, bookingId, navigate]);

  if (!booking) return null;

  const getStatusBadge = () => {
    if (booking.status === "CONFIRMED") return "bg-success";
    if (booking.status === "CANCELLED") return "bg-danger";
    return "bg-warning text-dark";
  };

  return (
    <div className="container mt-5">
      <div className="card shadow p-4 mx-auto" style={{ maxWidth: "600px" }}>
        <h3 className="text-center mb-4">Booking Summary</h3>

        <p><strong>Booking ID:</strong> {booking.id}</p>

        <p>
          <strong>Status:</strong>{" "}
          <span className={`badge ${getStatusBadge()}`}>
            {booking.status}
          </span>
        </p>

        <p><strong>Seats:</strong> {booking.seatIds?.join(", ")}</p>

        <p>
          <strong>Total Amount:</strong>{" "}
          <span className="fw-bold text-primary">
            ₹{booking.totalAmount}
          </span>
        </p>

        {booking.status === "PENDING_PAYMENT" && (
          <>
            <div className="alert alert-info text-center mt-3">
              Please complete payment within 5 minutes.
            </div>

            <div className="d-flex justify-content-between mt-4">
              <button
                className="btn btn-outline-secondary"
                onClick={() => navigate("/")}
              >
                Cancel
              </button>

              <button
                className="btn btn-primary px-4"
                onClick={() => navigate(`/payment/${booking.bookingId}`)}

              >
                Proceed to Payment
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}

export default BookingSummary;
