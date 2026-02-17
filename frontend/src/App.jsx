import AppRoutes from "./routes/AppRoutes";
import Navbar from "./components/layout/navbar";

function App() {
  return (
    <div className="d-flex flex-column min-vh-100 bg-light">
      
      {/* Top Navigation */}
      <Navbar />

      {/* Main Content Area */}
      <main className="flex-grow-1">
        <div className="container py-4">
          <AppRoutes />
        </div>
      </main>

      {/* Optional Future Footer Space */}
      <footer className="bg-dark text-light text-center py-3 mt-auto">
        <small>© {new Date().getFullYear()} CinemaHub</small>
      </footer>

    </div>
  );
}

export default App;
