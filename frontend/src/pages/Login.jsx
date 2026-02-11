import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../api/authApi";

const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await loginUser(email, password);

            localStorage.setItem("userId", response.data.userId);
            localStorage.setItem("email", response.data.email);

            alert("Login successful!");
            navigate("/");
        } catch (error) {
            alert("Invalid credentials");
            console.error(error);
        }
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit = {handleSubmit}>
                <input 
                    type="text"
                    placeholder="Email"
                    value = {email}
                    onChange = {(e) => setEmail(e.target.value)}
                    required
                 />
                 <br /><br />
                 <input 
                    type="password"
                    placeholder="Password"
                    value = {password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                 />
                 <br /><br />
                 <button type = "submit">Login</button>
            </form>
        </div>
    );
};

export default Login;