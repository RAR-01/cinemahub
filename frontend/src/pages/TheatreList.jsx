import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "../api/axios";

function TheatreList() {
    const { movieId } = useParams();
    const navigate = useNavigate();
    const [theatres, setTheatres] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios
            .get(`/theatres/movies/${movieId}`)
            .then(res => {
                setTheatres(res.data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setLoading(false);
            });
    }, [movieId]);

    if (loading) return <p>Loading Theatres...</p>;

    return (
        <div>
            <h1>Select Theatre</h1>

            {theatres.length === 0 && <p>No theatres available</p>}

            {theatres.map(theatre => (
                <div
                    key={theatre.id}
                    onClick={() =>
                        navigate(
                          `/movies/${movieId}/theatres/${theatre.id}/shows`
                        )
                    }
                    style={{
                        border: "1px solid #ccc",
                        padding: "10px",
                        marginBottom: "10px",
                        cursor: "pointer"
                    }}
                >
                    <h3>{theatre.name}</h3>
                    <p>{theatre.city}</p>
                </div>
            ))}
        </div>
    );
}

export default TheatreList;
