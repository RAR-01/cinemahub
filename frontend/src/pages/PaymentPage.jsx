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

    axios
      .post(`/payments/initiate/${bookingId}`)
      .then((res) => {
        setPayment(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        alert("Payment initiation failed");
        navigate("/");
      });
  }, [bookingId, navigate]);

  // Countdown
  useEffect(() => {
    if (!payment) return;

    const timer = setInterval(() => {
      setTimeLeft((prev) => {
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

      await axios.post(
        `/payments/${payment.paymentId}/success`
      );

      navigate(`/booking/${bookingId}`);
    } catch (err) {
      console.error(err);
      alert("Payment failed");
      setProcessing(false);
    }
  };

  const handleFailure = async () => {
    try {
      await axios.post(
        `/payments/${payment.paymentId}/failure`
      );
      navigate("/");
    } catch (err) {
      console.error(err);
    }
  };

  if (loading) {
    return (
      <div className="container py-5 text-center">
        <div
          className="spinner-border text-primary"
          style={{ width: "3rem", height: "3rem" }}
        />
        <p className="mt-4 fs-5 text-muted">
          Initiating payment...
        </p>
      </div>
    );
  }

  return (
    <div className="container py-5 d-flex justify-content-center">
      <div
        className="card border-0 shadow-lg p-4"
        style={{ maxWidth: "500px", width: "100%" }}
      >
        {/* Header */}
        <div className="text-center mb-4">
          <h3 className="fw-bold">Secure Payment</h3>
          <p className="text-muted mb-0">
            Complete your booking
          </p>
        </div>

        <hr />

        {/* Booking Info */}
        <div className="mb-3">
          <p className="mb-2">
            <strong>Booking ID:</strong>{" "}
            {payment.bookingId}
          </p>
        </div>

        {/* Amount */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <span className="fw-semibold fs-5">
            Amount Payable
          </span>
          <span className="fw-bold fs-3 text-primary">
            ₹{payment.amount}
          </span>
        </div>

        {/* Timer */}
        <div className="alert alert-warning text-center">
          ⏳ Time Remaining:{" "}
          <strong className="fs-5">
            {formatTime()}
          </strong>
        </div>

        {/* Buttons */}
        <div className="d-grid gap-3 mt-3">
          <button
            className="btn btn-success btn-lg"
            onClick={handleSuccess}
            disabled={processing || timeLeft === 0}
          >
            {processing ? "Processing..." : "Pay Now"}
          </button>

          <button
            className="btn btn-outline-danger"
            onClick={handleFailure}
            disabled={processing}
          >
            Cancel Payment
          </button>
        </div>
      </div>
    </div>
  );
}

export default PaymentPage;
