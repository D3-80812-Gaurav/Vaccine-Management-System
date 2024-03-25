import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import Footer from '../components/Footer'
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import axios from 'axios';

export default function CitizenRegistration() {
    const [aadharId, setAadharId] = useState();
    const [email, setEmail] = useState("");
    const [phoneNo, setPhoneNo] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [formErrors, setFormErrors] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        if (confirmPassword === password)
            registerCitizen();
        else
            setFormErrors("Passwords don't match. Please try again.");
    }
    const registerCitizen = () => {
        const baseURL = process.env.REACT_APP_API_URL;
        let data = JSON.stringify({ "aadharId": aadharId, "email": email, "phoneNo": phoneNo, "password": password });

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/register',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                setAadharId("");
                setEmail("");
                setPhoneNo("");
                setPassword("");
                setConfirmPassword("");
                setFormErrors("");
                navigate("/citizen_services");
                toast.success("Registration Successful");
            })
            .catch((error) => {
                console.log(error);
            });
    }
    return (
        <>
            <Navbar></Navbar>
            <div className="container container-fluid">
                <div className="container">
                    <div className="row">
                        <div className="col">
                        </div>
                        <div className="col">
                            <div className="container border rounded mt-4 shadow p-3">
                                <form action="submit">
                                    <div className="mb-1">
                                        <div className="text-center fw-bolder"><h1>Register</h1></div>
                                        <label htmlFor="aadharId" className="form-label">Aadhar ID:</label>
                                        <input type="number" className="form-control" id="aadharId" placeholder="Enter AadharID" value={aadharId} onChange={(e) => setAadharId(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-1">
                                        <label htmlFor="email" className="form-label">Email ID:</label>
                                        <input type="email" className="form-control" id="email" placeholder="example@example.com" value={email} onChange={(e) => setEmail(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-1">
                                        <label htmlFor="phoneNo" className="form-label">Phone No:</label>
                                        <input type="text" className="form-control" id="phoneNo" placeholder="Enter Phone No" value={phoneNo} onChange={(e) => setPhoneNo(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-1">
                                        <label htmlFor="password" className="form-label">Password:</label>
                                        <input type="password" className="form-control" id="password" placeholder="Enter Your Password" value={password} onChange={(e) => setPassword(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-1">
                                        <label htmlFor="confirmPassword" className="form-label">Confirm Password:</label>
                                        <input type="password" className="form-control" id="confirmPassword" placeholder="Confirm Your Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                                            required />
                                    </div>
                                    {(formErrors.length != 0) ? <h6 className='text-danger text-center'>{formErrors}</h6> : <></>}
                                    <div className="mb-1">
                                        <div class="d-grid gap-2 text-center">
                                            <button type="submit" className="btn btn-primary mt-2" onClick={handleSubmit}>Submit</button>
                                            <h6 className='mt-2'>Don't have an account? Visit Nearest PMC Office</h6>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div className="col">
                        </div>
                    </div>
                </div >
            </div >
            <Footer></Footer>
        </>
    )
}
