import axios from "./axios";

export const createBooking = (showId, seatIds) => {
    const userId = localStorage.getItem("userId");

    return axios.post("/bookings", {
        showId,
        seatIds,
        userId: Number(userId)
    });
};
