import { Link, useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();

  const userId = localStorage.getItem("userId");
  const email = localStorage.getItem("email");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">

        {/* Brand */}
        <Link className="navbar-brand fw-bold" to="/">
          🎬 CinemaHub
        </Link>

        <div className="collapse navbar-collapse">
          <ul className="navbar-nav ms-auto align-items-center">

            {userId ? (
              <>
                {/* My Bookings Button */}
                <li className="nav-item me-3">
                  <button
                    className="btn btn-outline-light btn-sm"
                    onClick={() => navigate("/my-bookings")}
                  >
                    My Bookings
                  </button>
                </li>

                {/* User Email */}
                <li className="nav-item me-3 text-light">
                  {email}
                </li>

                {/* Logout */}
                <li className="nav-item">
                  <button
                    className="btn btn-outline-danger btn-sm"
                    onClick={handleLogout}
                  >
                    Logout
                  </button>
                </li>
              </>
            ) : (
              <li className="nav-item">
                <Link className="btn btn-outline-light btn-sm" to="/login">
                  Login
                </Link>
              </li>
            )}

          </ul>
        </div>

      </div>
    </nav>
  );
}

export default Navbar;
