import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/login";
import Home from "./pages/home";
import { ThemeProvider } from "./components/theme-provider";

function App() {
  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </Router>
    </ThemeProvider>
  );
}
export default App;
