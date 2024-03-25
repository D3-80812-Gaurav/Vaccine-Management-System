import React, { useState } from 'react'
import axios from 'axios';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import SubNavbar from './SubNavbar';

export default function AddCenterForm() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [stock, setStock] = useState();
  const [state, setState] = useState("");
  const [district, setDistrict] = useState("");
  const [city, setCity] = useState("");
  const [pincode, setPincode] = useState("");
  const [password, setPassword] = useState("");
  const handleAddCenter = (e) => {
    e.preventDefault();
    addCenter();
    setName("");
    setStock("");
    setState("");
    setDistrict("");
    setCity("");
    setPincode("");
    setPassword("");
    setEmail("");
  }

  const addCenter = () => {
    const baseURL = process.env.REACT_APP_API_URL;
    let data = JSON.stringify({ "name": name, "state": state, "district": district, "city": city, "pinCode": pincode, "email": email, "stock": stock, "password": password });
    const vpAtoken = sessionStorage.getItem("vpAtoken");
    let config = {
      method: 'post',
      maxBodyLength: Infinity,
      url: baseURL + 'admin/register_new_center',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + vpAtoken
      },
      data: data
    };

    axios.request(config)
      .then((response) => {
        toast.success("Added Center Successfully");
      })
      .catch((error) => {
        toast.warn("Something Went Wrong");
      });
  }
  return (
    <>
      <SubNavbar></SubNavbar>
      <div className="container container-fluid">
        <div className="container">
          <form>
            <div className="container border rounded mt-2 shadow p-3 w-50">
              <h1 className='text-center mb-3'>Add New Center</h1>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder="Enter Center name" id='name' value={name} onChange={(e) => setName(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="email" className="form-control" placeholder="Enter Center Email" id='email' value={email} onChange={(e) => setEmail(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="number" className="form-control" placeholder="Stock" id='stock' min={0} value={stock} onChange={(e) => setStock(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder=" Enter State" id='state' value={state} onChange={(e) => setState(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder="Enter District" id='district' value={district} onChange={(e) => setDistrict(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder="Enter City" id='city' value={city} onChange={(e) => setCity(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="text" className="form-control" placeholder="Enter PINCODE" id='pincode' value={pincode} onChange={(e) => setPincode(e.target.value)}
                  required />
              </div>
              <div className="mb-3">
                <input type="password" className="form-control" placeholder="Enter Password" id='password' value={password} onChange={(e) => setPassword(e.target.value)}
                  required />
              </div>
              <div className="d-grid gap-2 text-center">
                <button className='btn btn-primary' onClick={handleAddCenter}>Add Center</button>
              </div>
            </div>
          </form>
        </div>
      </div >
    </>
  )
}
