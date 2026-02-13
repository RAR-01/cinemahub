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

    if (loading) {
        return (
            <div className="container mt-5 text-center">
                <div className="spinner-border text-primary"></div>
                <p className="mt-3">Loading Theatres...</p>
            </div>
        );
    }

    return (
        <div className="container mt-5">
            <h2 className="mb-4 text-center">Select Theatre</h2>

            {theatres.length === 0 && (
                <div className="alert alert-warning text-center">
                    No theatres available
                </div>
            )}

            <div className="list-group">
                {theatres.map(theatre => (
                    <div
                        key={theatre.id}
                        className="list-group-item list-group-item-action shadow-sm mb-2 rounded"
                        style={{ cursor: "pointer" }}
                        onClick={() =>
                            navigate(
                                `/movies/${movieId}/theatres/${theatre.id}/shows`
                            )
                        }
                    >
                        <h5 className="mb-1">{theatre.name}</h5>
                        <p className="mb-0 text-muted">{theatre.city}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default TheatreList;
