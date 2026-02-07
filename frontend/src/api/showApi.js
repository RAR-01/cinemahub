import axios from "./axios";


export const getShowsByMovie = (movieId) =>
    axios.get(`/shows/movie/${movieId}`);