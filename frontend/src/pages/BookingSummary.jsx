import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "../api/axios";

function BookingSummary() {
  const location = useLocation();
  const navigate = useNavigate();
  const { bookingId } = useParams();

  const [booking, setBooking] = useState(location.state || null);

  useEffect(() => {
    if (!booking && bookingId) {
      axios
        .get(`/bookings/${bookingId}`)
        .then((res) => setBooking(res.data))
        .catch(() => navigate("/"));
    }
  }, [booking, bookingId, navigate]);

  if (!booking) {
    return (
      <div className="container py-5 text-center">
        <div
          className="spinner-border text-primary"
          style={{ width: "3rem", height: "3rem" }}
        />
        <p className="mt-4 text-muted">Loading booking details...</p>
      </div>
    );
  }

  const getStatusBadge = () => {
    if (booking.status === "CONFIRMED") return "bg-success";
    if (booking.status === "CANCELLED") return "bg-danger";
    return "bg-warning text-dark";
  };

  return (
    <div className="container py-5 d-flex justify-content-center">
      <div
        className="card border-0 shadow-lg p-4"
        style={{ maxWidth: "650px", width: "100%" }}
      >
        {/* Header */}
        <div className="text-center mb-4">
          <h3 className="fw-bold">Booking Summary</h3>
          <p className="text-muted mb-0">
            Review your booking details
          </p>
        </div>

        <hr />

        {/* Booking Details */}
        <div className="mb-3">
          <p className="mb-2">
            <strong>Booking ID:</strong> {booking.id}
          </p>

          <p className="mb-2">
            <strong>Status:</strong>{" "}
            <span className={`badge ${getStatusBadge()} px-3 py-2`}>
              {booking.status}
            </span>
          </p>

          <p className="mb-2">
            <strong>Seats:</strong>{" "}
            {booking.seatIds?.join(", ")}
          </p>
        </div>

        <hr />

        {/* Total */}
        <div className="d-flex justify-content-between align-items-center mb-3">
          <span className="fw-semibold fs-5">
            Total Amount
          </span>
          <span className="fw-bold fs-4 text-primary">
            ₹{booking.totalAmount}
          </span>
        </div>

        {/* Payment Section */}
        {booking.status === "PENDING_PAYMENT" && (
          <>
            <div className="alert alert-info text-center mt-3">
              Please complete payment within 5 minutes.
            </div>

            <div className="d-flex justify-content-between mt-4">
              <button
                className="btn btn-outline-secondary px-4"
                onClick={() => navigate("/")}
              >
                Cancel
              </button>

              <button
                className="btn btn-primary px-4"
                onClick={() =>
                  navigate(`/payment/${booking.bookingId}`)
                }
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
