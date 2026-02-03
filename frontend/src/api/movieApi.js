import axios from "./axios";


export const getAllMovies = () => {
    return axios.get("/movies");
};

export const getMovieById = (id) => {
    return axios.get(`/movies/${id}`);
};