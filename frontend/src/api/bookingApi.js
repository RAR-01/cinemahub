import axios from "./axios";

export const createBooking = (showId, seatIds) => {
    return axios.post("/bookings", {
        showId,
        seatIds
    });
};