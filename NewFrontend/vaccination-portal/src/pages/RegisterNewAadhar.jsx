import React from 'react'
import Navbar from '../components/Navbar'
import { useState } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';

export default function RegisterNewAadhar() {
    const url = "http://localhost:8080/admin/register_new_aadhar_card"
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");
    const [state, setState] = useState("");
    const [district, setDistrict] = useState("");
    const [city, setCity] = useState("");
    const [pinCode, setPincode] = useState("");

    const handleAddAadharCard = () => {
        addAadharCard();
        setFirstName("");
        setLastName("");
        setDob("");
        setGender("");
        setState("");
        setDistrict("");
        setCity("");
        setPincode("");
    }

    const addAadharCard = () => {
        const data = {
            "firstName": firstName,
            "lastName": lastName,
            "dob": dob,
            "gender": gender,
            "state": state,
            "district": district,
            "city": city,
            "pinCode": pinCode,
        }
        axios.post(url, data).then((response) => {
            toast.success("Aadhar Registered Successfully");
        }).catch((error) => {
            toast.error("Unable to Register Aadhar");
        })
    }

    return (
        <>
            <Navbar />
            <div className="container w-50 border rounded shadow mt-5">
                <h1 className="text-center pt-2 mb-2">Register New Aadhar</h1>
                <div className="container text-center mt-2">
                    <div class="row my-3">
                        <div class="col">
                            <input type="text" class="form-control" placeholder="First name" aria-label="First name" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" placeholder="Last name" aria-label="Last name" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col">
                            <input type="date" class="form-control" placeholder="Date Of Birth" aria-label="Date Of Birth" value={dob} onChange={(e) => setDob(e.target.value)} required />
                        </div>
                        <div class="col">
                            <select class="form-select form-select" aria-label="Gender" value={gender} onChange={(e) => setGender(e.target.value)} required >
                                <option selected>Open to select gender</option>
                                <option value="MALE" >Male</option>
                                <option value="FEMALE">Female</option>
                                <option value="OTHER">Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col">
                            <input type="text" class="form-control" placeholder="State" aria-label="State" value={state} onChange={(e) => setState(e.target.value)} required />
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" placeholder="District" aria-label="District" value={district} onChange={(e) => setDistrict(e.target.value)} required />
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col">
                            <input type="text" class="form-control" placeholder="City" aria-label="City" value={city} onChange={(e) => setCity(e.target.value)} required />
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" placeholder="Pincode" aria-label="Pincode" value={pinCode} onChange={(e) => setPincode(e.target.value)} required />
                        </div>
                    </div>
                    <div class="row my-3">
                        <div class="col">
                            <button className='btn btn-primary' onClick={handleAddAadharCard}>Create New Aadhar Card</button>
                        </div>
                    </div>
                </div>
            </div>
            <ToastContainer />
        </>
    )
}
