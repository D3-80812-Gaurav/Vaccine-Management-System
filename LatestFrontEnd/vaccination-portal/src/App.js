import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
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
import PrintAadharCardWindow from './pages/PrintAadharCardWindow';
import ModifyCenter from './pages/ModifyCenter';
import CitizenRegistration from './pages/CitizenRegistration';

function App() {
  const loginStatus = () => {
    const userToken = sessionStorage.getItem("vpToken");
    const adminToken = sessionStorage.getItem("vpAtoken");
    const centerToken = sessionStorage.getItem("vpCtoken");
    if (userToken !== null || adminToken !== null || centerToken !== null)
      return true;
    else
      return false;
  }
  return (
    <>
      <Router>
        <Routes>
          <Route index element={<Home />}></Route>
          <Route path='/home' element={<Home />}></Route>
          <Route path='/center' element={<CenterSignIn />}></Route>
          <Route path='/admin_services' element={<AdminServices />}></Route>
          <Route path='/citizen_services' element={<CitizenServices />}></Route>
          <Route path='/faq' element={<FAQ />}></Route>
          <Route path='/citizen_registration' element={<CitizenRegistration />}></Route>
          <Route path='/stats' element={<Stats />}></Route>

          <Route path='/citizen_dashboard' element={loginStatus() ? <CitizenDashboard /> : <Navigate to="/citizen_services" />}></Route>
          <Route path='/appointment_details' element={loginStatus() ? <Appointment /> : <Navigate to="/citizen_services" />}></Route>
          <Route path='/book_slot' element={loginStatus() ? <BookSlot /> : <Navigate to="/citizen_services" />}></Route>
          <Route path='/book/:id' element={loginStatus() ? <BookingWindow /> : <Navigate to="/citizen_services" />}></Route>
          <Route path='/download_certificate' element={loginStatus() ? <DownloadCertificate /> : <Navigate to="/citizen_services" />}></Route>

          <Route path='/admin_dashboard' element={loginStatus() ? <AdminDashboard /> : <Navigate to="/admin_services" />}></Route>
          <Route path='/add_center' element={loginStatus() ? <AddCenter /> : <Navigate to="/admin_services" />}></Route>
          <Route path='/register_aadhar' element={loginStatus() ? <RegisterNewAadhar /> : <Navigate to="/admin_services" />}></Route>
          <Route path='/print_aadhar' element={loginStatus() ? <PrintAadharCardWindow /> : <Navigate to="/admin_services" />}></Route>
          <Route path='/modify_center' element={loginStatus() ? <ModifyCenter /> : <Navigate to="/admin_services" />}></Route>

          <Route path='/center_dashboard' element={loginStatus() ? <CenterDashboard /> : <Navigate to="/center" />}></Route>
        </Routes>
      </Router >
      <ToastContainer />
    </>
  );
}

export default App;
