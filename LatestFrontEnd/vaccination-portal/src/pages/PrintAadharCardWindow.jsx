import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar'
import axios from 'axios';
import { toast } from 'react-toastify';

export default function PrintAadharCardWindow() {
    const [id, setId] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");
    const [state, setState] = useState("");
    const [city, setCity] = useState("");
    const [pincode, setPincode] = useState("");

    const [aadharId, setAadharId] = useState("");
    const handleSearch = () => {
        setAadharId();
        const baseURL = process.env.REACT_APP_API_URL;
        const vpAtoken = sessionStorage.getItem("vpAtoken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'admin/get_aadhar_card/' + aadharId,
            headers: {
                'Authorization': 'Bearer ' + vpAtoken
            }
        };

        axios.request(config)
            .then((response) => {
                console.log(response.data);
                const res = response.data;
                toast.success("Found Aadhar");
                setAadharId("");
                setId(res.id);
                setFirstName(res.firstName);
                setLastName(res.lastName);
                setDob(res.dob);
                setGender(res.gender);
                setCity(res.city);
                setState(res.state);
                setPincode(res.pinCode);
            })
            .catch((error) => {
                console.log(error);
                setAadharId("");
                setId("");
                setFirstName("");
                setLastName("");
                setDob("");
                setGender("");
                setCity("");
                setState("");
                setPincode("");
            });
    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <div className="container text-center mt-2  ">
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Enter Aadhar Card ID:" required value={aadharId} onChange={(e) => setAadharId(e.target.value)} />
                        <button class="btn btn-outline-success" type="button" onClick={handleSearch}>Search</button>
                    </form>
                </div>
                {(firstName.length == 0) &&
                    <div className="container text-center mt-2  text-danger">
                        <h3>{id}</h3>
                        <h5>Looks this Aadhar Does Not Exist Search 🔍 for Another AadharID</h5>
                    </div>}
                {(firstName.length >= 0) &&
                    (
                        <>
                            <div className="container mt-5">
                                <div class="card mb-3">
                                    <div class="row g-0">
                                        <div class="col-md-4">
                                            <img src="..." class="img-fluid rounded-start" alt="..." />
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h5 class="card-title">Aadhar Card</h5>
                                                <p class="card-text">Name: {firstName + " " + lastName}</p>
                                                <p class="card-text">AadharID: {id}</p>
                                                <p class="card-text">DOB: {dob}</p>
                                                <p class="text">Gender: {gender}</p>
                                                <p class="text">Address: {city + ", " + state + ", pincode:" + pincode}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </>
                    )}
            </div >
        </>
    )
}
