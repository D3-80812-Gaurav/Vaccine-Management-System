import React, { useState } from 'react'
import usersignin from '../images/usersignin.svg';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

export default function CitizenLogin() {
    const navigate = useNavigate();
    const ur = "http://localhost:8080/citizen/signin";
    const [aadharId, setAddharId] = useState();
    const [password, setPassword] = useState("");
    const [formErrors, setFormErrors] = useState("");
    const handleSubmit = (e) => {
        e.preventDefault();
        loginCitizen();
        setAddharId("");
        setPassword("");
    }

    const loginCitizen = async () => {
        let data = {
            "aadharID": Number.parseInt(aadharId),
            "password": password,
        }
        axios.post(ur, data).then((response) => {
            sessionStorage.setItem("userDetails", JSON.stringify(response.data));
            //const a = sessionStorage.getItem("userDetails");
            navigate('/citizen_dashboard');
            // console.log(a);
            // console.log(a.citizenID);
            toast.success("Login Successfully");
        }).catch((error) => {
            console.log(error);
            toast.error("Login Failure !");
        })
    };


    return (
        <>
            <div className="container container-fluid">
                <div className="container">
                    <div className="row">
                        <div className="col">
                        </div>
                        <div className="col">
                            <div className="container border rounded mt-4 shadow">
                                <form action="submit">
                                    <div className="mb-3">
                                        <img src={usersignin} style={{ width: 400 }} className="rounded mx-auto d-block my-2"></img>
                                        <label htmlFor="aadharId" className="form-label">Aadhar Id:</label>
                                        <input type="number" className="form-control" id="aadharId" placeholder="Aadhar ID" value={aadharId} onChange={(e) => setAddharId(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="password" className="form-label">Password:</label>
                                        <input type="password" className="form-control" id="password" placeholder="Enter Your Password" value={password} onChange={(e) => setPassword(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-3">
                                        <button type="submit" className="btn btn-primary mb-3 me-2" onClick={handleSubmit}>Submit</button>
                                        <button type="reset" className="btn btn-primary mb-3">Reset</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div className="col">
                        </div>
                    </div>
                </div>
            </div>
            <ToastContainer />
        </>
    )
}
