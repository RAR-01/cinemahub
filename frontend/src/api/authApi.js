import axios from "./axios";


export const registerUser = (name, email, password) => {
    return axios.post("/auth/register", {
        name,
        email,
        password
    });
};

export const loginUser = (email, password) => {
    return axios.post("/auth/login", {
        email,
        password
    });
};