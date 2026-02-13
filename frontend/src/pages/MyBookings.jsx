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
      .then(res => {
        setBookings(res.data);
        setLoading(false);
      })
      .catch(err => {
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
    if (status === "PENDING_PAYMENT") return "bg-warning text-dark";
    return "bg-secondary";
  };

  if (loading) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border text-primary"></div>
        <p className="mt-3">Loading your bookings...</p>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-center">My Bookings</h2>

      {bookings.length === 0 ? (
        <div className="alert alert-info text-center">
          You have no bookings yet.
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-bordered table-hover shadow">
            <thead className="table-dark">
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
              {bookings.map(booking => (
                <tr key={booking.bookingId}>
                  <td>{booking.movieTitle}</td>

                  <td>
                    {booking.theatreName}
                    <br />
                    <small className="text-muted">
                      {booking.screenName}
                    </small>
                  </td>

                  <td>{formatDateTime(booking.showTime)}</td>

                  <td>{booking.seatNumbers.join(", ")}</td>

                  <td className="fw-bold text-primary">
                    ₹{booking.totalAmount}
                  </td>

                  <td>
                    <span className={`badge ${getStatusBadge(booking.status)}`}>
                      {booking.status}
                    </span>
                  </td>

                  <td>{formatDateTime(booking.bookedAt)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default MyBookings;
