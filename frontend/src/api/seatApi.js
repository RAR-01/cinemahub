import api from "./axios";

export const getSeatsByScreen = (screenId) =>
  api.get(`/seats/screen/${screenId}`);

export const lockSeats = (seatIds) =>
  api.post("/seats/lock", seatIds);
