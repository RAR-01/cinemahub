import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../api/authApi";

const Register = () => {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await registerUser(name, email, password);
            alert("Registration successful!");
            navigate("/login");
        } catch (error) {
            alert("Registration failed");
            console.error(error);
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100">
            <div className="card p-4 shadow" style={{ width: "400px" }}>
                <h3 className="text-center mb-4">Register</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">Name</label>
                        <input
                            type="text"
                            className="form-control"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            minLength="6"
                        />
                        <div className="form-text">
                            Password must be at least 6 characters.
                        </div>
                    </div>

                    <button type="submit" className="btn btn-success w-100">
                        Register
                    </button>
                </form>

                <hr />

                <div className="text-center">
                    <p className="mb-2">Already have an account?</p>
                    <button
                        className="btn btn-outline-secondary w-100"
                        onClick={() => navigate("/login")}
                    >
                        Back to Login
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Register;
