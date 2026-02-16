import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "../api/axios";

function PaymentPage() {
  const { bookingId } = useParams();
  const navigate = useNavigate();

  const [payment, setPayment] = useState(null);
  const [loading, setLoading] = useState(true);
  const [processing, setProcessing] = useState(false);
  const [timeLeft, setTimeLeft] = useState(300);

  // Initiate payment
  useEffect(() => {
    if (!bookingId) return;

    axios.post(`/payments/initiate/${bookingId}`)
      .then(res => {
        setPayment(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        alert("Payment initiation failed");
        navigate("/");
      });
  }, [bookingId, navigate]);

  // Countdown
  useEffect(() => {
    if (!payment) return;

    const timer = setInterval(() => {
      setTimeLeft(prev => {
        if (prev <= 1) {
          clearInterval(timer);
          handleFailure();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [payment]);

  const formatTime = () => {
    const minutes = Math.floor(timeLeft / 60);
    const seconds = timeLeft % 60;
    return `${minutes}:${seconds.toString().padStart(2, "0")}`;
  };

  const handleSuccess = async () => {
    try {
      setProcessing(true);

      await axios.post(`/payments/${payment.paymentId}/success`);

      alert("Payment successful!");

      navigate(`/booking/${bookingId}`);
    } catch (err) {
      console.error(err);
      alert("Payment failed");
      setProcessing(false);
    }
  };

  const handleFailure = async () => {
    try {
      await axios.post(`/payments/${payment.paymentId}/failure`);
      alert("Payment failed");
      navigate("/");
    } catch (err) {
      console.error(err);
    }
  };

  if (loading) {
    return (
      <div className="container mt-5 text-center">
        <div className="spinner-border text-primary"></div>
        <p className="mt-3">Initiating Payment...</p>
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <div className="card shadow p-4 mx-auto" style={{ maxWidth: "500px" }}>
        <h3 className="text-center mb-3">Payment</h3>

        <p><strong>Booking ID:</strong> {payment.bookingId}</p>

        <p>
          <strong>Amount:</strong>{" "}
          <span className="fw-bold text-primary">
            ₹{payment.amount}
          </span>
        </p>

        <div className="alert alert-warning text-center">
          ⏳ Time Remaining: <strong>{formatTime()}</strong>
        </div>

        <div className="d-flex justify-content-between mt-3">
          <button
            className="btn btn-success"
            onClick={handleSuccess}
            disabled={processing || timeLeft === 0}
          >
            Pay Now
          </button>

          <button
            className="btn btn-outline-danger"
            onClick={handleFailure}
            disabled={processing}
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default PaymentPage;
