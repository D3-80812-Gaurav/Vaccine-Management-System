import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import CenterSignIn from './pages/CenterSignIn';
import CitizenServices from './pages/CitizenServices';
import FAQ from './pages/FAQ';
import AdminServices from './pages/AdminServices';
import { ToastContainer } from 'react-toastify';
import CitizenDashboard from './pages/CitizenDashboard';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route index element={<Home />}></Route>
          <Route path='/home' element={<Home />}></Route>
          <Route path='/center' element={<CenterSignIn />}></Route>
          <Route path='/admin_services' element={<AdminServices />}></Route>
          <Route path='/citizen_services' element={<CitizenServices />}></Route>
          <Route path='/citizen_dashboard' element={<CitizenDashboard />}></Route>
          <Route path='/faq' element={<FAQ />}></Route>
        </Routes>
      </Router>
      <ToastContainer />
    </>
  );
}

export default App;
