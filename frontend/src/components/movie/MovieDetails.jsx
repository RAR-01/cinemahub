import { useParams } from "react-router-dom";


const MovieDetails = () => {
    const { movieId } = useParams();

    return (
        <div>
            <h2>Movie Details</h2>
            <p>Movie ID: {movieID}</p>
            
        </div>
    );
};

export default MovieDetails;