import { useLocation, useNavigate } from "react-router-dom";

function BookingSummary() {
  const { state } = useLocation();
  const navigate = useNavigate();

  if (!state) {
    return (
      <div className="container mt-5 text-center">
        <div className="alert alert-danger">
          Invalid booking details.
        </div>
      </div>
    );
  }

  const getStatusBadge = () => {
    if (state.status === "CONFIRMED") {
      return "bg-success";
    }
    if (state.status === "CANCELLED") {
      return "bg-danger";
    }
    return "bg-warning text-dark"; // PENDING_PAYMENT
  };

  return (
    <div className="container mt-5">
      <div className="card shadow p-4 mx-auto" style={{ maxWidth: "600px" }}>
        
        <h3 className="text-center mb-4">Booking Summary</h3>

        <div className="mb-3">
          <p><strong>Booking ID:</strong> {state.bookingId}</p>

          <p>
            <strong>Status:</strong>{" "}
            <span className={`badge ${getStatusBadge()}`}>
              {state.status}
            </span>
          </p>

          <p><strong>Seats:</strong> {state.seatIds.join(", ")}</p>

          <p>
            <strong>Total Amount:</strong>{" "}
            <span className="fw-bold text-primary">
              ₹{state.totalAmount}
            </span>
          </p>
        </div>

        <div className="alert alert-info text-center">
          Please complete payment within 5 minutes to confirm your booking.
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
            onClick={() => navigate(`/payment/${state.bookingId}`)}
          >
            Proceed to Payment
          </button>

        </div>

      </div>
    </div>
  );
}

export default BookingSummary;
