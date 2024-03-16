import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function AddCenterForm() {

  const ur = "http://localhost:8080/admin/register_new_center";
  const [name, setName] = useState("");
  const [stock, setStock] = useState(0);
  const [state, setState] = useState("");
  const [district, setDistrict] = useState("");
  const [city, setCity] = useState("");
  const [pincode, setPincode] = useState("");
  const [password, setPassword] = useState("");
  const handleAddCenter = (e) => {
    e.preventDefault();
    addCenter();
    setName("");
    setStock(0);
    setState("");
    setDistrict("");
    setCity("");
    setPincode("");
    setPassword("");
  }

  const addCenter = () => {
    let data = {
      "name": name,
      "state": state,
      "district": district,
      "city": city,
      "pinCode": pincode,
      "stock": stock,
      "password": password
    }
    axios.post(ur, data).then((response) => {
      toast.success("Center Added Successfully");
    }).catch((error) => {
      toast.error("Unable to Add Center");
    })
  }
  return (
    <>
      <form>
        <div className="container border rounded mt-4 shadow p-4">
          <h1 className='text-center mb-3'>Add Center</h1>
          <div className="container text-center mt-2">
            <div class="row">
              <div class="col">
                <input type="text" class="form-control" placeholder="Enter Center name" id='name' value={name} onChange={(e) => setName(e.target.value)}
                  required />
              </div>
              <div class="col">
                <input type="number" class="form-control" placeholder="Stock" id='stock' min={0} value={stock} onChange={(e) => setStock(e.target.value)}
                  required />
              </div>
              <div class="col">
                <input type="text" class="form-control" placeholder=" Enter State" id='state' value={state} onChange={(e) => setState(e.target.value)}
                  required />
              </div>
            </div>
            <div class="row mt-2">
              <div class="col">
                <input type="text" class="form-control" placeholder="Enter District" id='district' value={district} onChange={(e) => setDistrict(e.target.value)}
                  required />
              </div>
              <div class="col">
                <input type="text" class="form-control" placeholder="Enter City" id='city' value={city} onChange={(e) => setCity(e.target.value)}
                  required />
              </div>
            </div>
            <div class="row mt-2">
              <div class="col">
                <input type="text" class="form-control" placeholder="Enter PINCODE" id='pincode' value={pincode} onChange={(e) => setPincode(e.target.value)}
                  required />
              </div>
              <div class="col">
                <input type="password" class="form-control" placeholder="Enter Password" id='password' value={password} onChange={(e) => setPassword(e.target.value)}
                  required />
              </div>
            </div>
            <div class="row mt-2 mx-5">
              <button className='btn btn-primary' onClick={handleAddCenter}>Add Center</button>
            </div>
          </div>
        </div >
      </form>
      <ToastContainer></ToastContainer>
    </>
  )
}
