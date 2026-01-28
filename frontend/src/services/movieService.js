const BASE_URL = "http://localhost:8080/api/movies";

export const getAllMovies = async () => {
  const response = await fetch(BASE_URL);
  if (!response.ok) {
    throw new Error("Failed to fetch movies");
  }
  return response.json();
};
