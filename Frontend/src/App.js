import Footer from "./components/Footer";
import Navbar from "./components/Navbar";
import { BrowserRouter } from "react-router-dom/cjs/react-router-dom.min";
import Launcher from "./components/Launcher";

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Launcher />
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
