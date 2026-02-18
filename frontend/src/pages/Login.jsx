import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../api/authApi";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("userId")) {
      navigate("/");
    }
  }, [navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await loginUser(email, password);

      localStorage.setItem("userId", response.data.userId);
      localStorage.setItem("email", response.data.email);

      navigate("/");
    } catch (error) {
      alert("Invalid credentials");
      console.error(error);
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{
        minHeight: "100vh",
        background: "linear-gradient(to right, #f8f9fa, #e9ecef)",
      }}
    >
      <div
        className="card border-0 shadow-lg p-4"
        style={{ width: "420px" }}
      >
        {/* Header */}
        <div className="text-center mb-4">
          <h3 className="fw-bold">Welcome Back</h3>
          <p className="text-muted mb-0">
            Login to continue booking
          </p>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label fw-semibold">
              Email
            </label>
            <input
              type="email"
              className="form-control py-2"
              value={email}
              onChange={(e) =>
                setEmail(e.target.value)
              }
              required
            />
          </div>

          <div className="mb-4">
            <label className="form-label fw-semibold">
              Password
            </label>
            <input
              type="password"
              className="form-control py-2"
              value={password}
              onChange={(e) =>
                setPassword(e.target.value)
              }
              required
            />
          </div>

          <button
            type="submit"
            className="btn btn-primary w-100 btn-lg"
          >
            Login
          </button>
        </form>

        <hr className="my-4" />

        <div className="text-center">
          <p className="mb-2 text-muted">
            Don’t have an account?
          </p>
          <button
            className="btn btn-outline-secondary w-100"
            onClick={() => navigate("/register")}
          >
            Create Account
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;
