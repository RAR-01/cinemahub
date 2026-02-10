import { useLocation } from "react-router-dom";

function BookingSummary() {
  const { state } = useLocation();

  if (!state) return <h3>Invalid booking</h3>;

  return (
    <div>
      <h2>Booking Created</h2>

      <p><b>Booking ID:</b> {state.bookingId}</p>
      <p><b>Status:</b> {state.status}</p>
      <p><b>Seats:</b> {state.seatIds.join(", ")}</p>
      <p><b>Total Amount:</b> ₹{state.totalAmount}</p>

      <p>Proceed to payment within 10 minutes.</p>
    </div>
  );
}

export default BookingSummary;
