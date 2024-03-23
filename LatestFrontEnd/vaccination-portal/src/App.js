import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import CenterSignIn from './pages/CenterSignIn';
import CitizenServices from './pages/CitizenServices';
import FAQ from './pages/FAQ';
import AdminServices from './pages/AdminServices';
import { ToastContainer } from 'react-toastify';
import CitizenDashboard from './pages/CitizenDashboard';
import Appointment from './pages/Appointment';
import BookSlot from './pages/BookSlot';
import BookingWindow from './pages/BookingWindow';
import DownloadCertificate from './pages/DownloadCertificate';
import AdminDashboard from './pages/AdminDashboard';
import AddCenter from './pages/AddCenter';
import RegisterNewAadhar from './pages/RegisterNewAadhar';
import CenterDashboard from './pages/CenterDashboard';
import Stats from './pages/Stats';

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
          <Route path='/appointment_details' element={<Appointment />}></Route>
          <Route path='/book_slot' element={<BookSlot />}></Route>
          <Route path='/faq' element={<FAQ />}></Route>
          <Route path='/book/:id' element={<BookingWindow />}></Route>
          <Route path='/download_certificate' element={<DownloadCertificate />}></Route>
          <Route path='/admin_dashboard' element={<AdminDashboard />}></Route>
          <Route path='/add_center' element={<AddCenter />}></Route>
          <Route path='/register_aadhar' element={<RegisterNewAadhar />}></Route>
          <Route path='/center_dashboard' element={<CenterDashboard />}></Route>
          <Route path='/stats' element={<Stats />}></Route>
        </Routes>
      </Router >
      <ToastContainer limit={1} />
    </>
  );
}

export default App;
