import { useEffect, useState } from "react";
import axios from "../api/axios";

function MyBookings() {
  const userId = localStorage.getItem("userId");

  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!userId) return;

    axios
      .get(`/bookings/user/${userId}`)
      .then((res) => {
        setBookings(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setLoading(false);
      });
  }, [userId]);

  const formatDateTime = (dateTime) => {
    return new Date(dateTime).toLocaleString();
  };

  const getStatusBadge = (status) => {
    if (status === "CONFIRMED") return "bg-success";
    if (status === "CANCELLED") return "bg-danger";
    if (status === "PENDING_PAYMENT")
      return "bg-warning text-dark";
    return "bg-secondary";
  };

  if (loading) {
    return (
      <div className="container py-5 text-center">
        <div
          className="spinner-border text-primary"
          style={{ width: "3rem", height: "3rem" }}
        />
        <p className="mt-4 fs-5 text-muted">
          Loading your bookings...
        </p>
      </div>
    );
  }

  return (
    <div className="container py-5">

      {/* Header */}
      <div className="text-center mb-5">
        <h2 className="fw-bold">My Bookings</h2>
        <p className="text-muted">
          View your past and upcoming reservations
        </p>
      </div>

      {bookings.length === 0 ? (
        <div className="alert alert-info text-center shadow-sm">
          You have no bookings yet.
        </div>
      ) : (
        <div className="card border-0 shadow-lg p-3">
          <div className="table-responsive">
            <table className="table align-middle mb-0">
              <thead className="table-light">
                <tr>
                  <th>Movie</th>
                  <th>Theatre</th>
                  <th>Show Time</th>
                  <th>Seats</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th>Booked At</th>
                </tr>
              </thead>
              <tbody>
                {bookings.map((booking) => (
                  <tr key={booking.bookingId}>
                    <td className="fw-semibold">
                      {booking.movieTitle}
                    </td>

                    <td>
                      {booking.theatreName}
                      <br />
                      <small className="text-muted">
                        {booking.screenName}
                      </small>
                    </td>

                    <td>
                      {formatDateTime(booking.showTime)}
                    </td>

                    <td>
                      {booking.seatNumbers.join(", ")}
                    </td>

                    <td className="fw-bold text-primary">
                      ₹{booking.totalAmount}
                    </td>

                    <td>
                      <span
                        className={`badge px-3 py-2 ${getStatusBadge(
                          booking.status
                        )}`}
                      >
                        {booking.status}
                      </span>
                    </td>

                    <td>
                      {formatDateTime(booking.bookedAt)}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}

export default MyBookings;
