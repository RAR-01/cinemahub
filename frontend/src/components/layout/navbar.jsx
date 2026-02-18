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
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm py-3">
      <div className="container">

        {/* Brand */}
        <Link className="navbar-brand fw-bold fs-4" to="/">
          🎬 CinemaHub
        </Link>

        {/* Mobile Toggle */}
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarContent"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarContent">
          <ul className="navbar-nav ms-auto align-items-lg-center gap-2">

            {userId ? (
              <>
                {/* My Bookings */}
                <li className="nav-item">
                  <button
                    className="btn btn-outline-light btn-sm"
                    onClick={() => navigate("/my-bookings")}
                  >
                    My Bookings
                  </button>
                </li>

                {/* Email */}
                <li className="nav-item text-light small px-2">
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
