import React, { useState } from 'react'
import usersignin from '../images/adminlogin.svg';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

export default function CenterLogin() {
    const navigate = useNavigate();
    const ur = "http://localhost:8080/center/login";
    const [centerId, setCenterId] = useState();
    const [password, setPassword] = useState("");
    const [formErrors, setFormErrors] = useState("");
    const handleSubmit = (e) => {
        login();
        e.preventDefault();
        setCenterId();
        setPassword("");
    }

    const login = async () => {
        let data = {
            "centerId": Number.parseInt(centerId),
            "password": password,
        }
        axios.post(ur, data).then((response) => {
            //navigate('/admin_dashboard');
            toast.success("Login Successfully");
            sessionStorage.setItem("centerId", centerId);
            navigate("/center_dashboard");
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
                                        <img src={usersignin} style={{ width: 350 }} className="rounded mx-auto d-block my-2"></img>
                                        <label htmlFor="name" className="form-label">Center ID:</label>
                                        <input type="text" className="form-control" id="centerId" placeholder="Enter Center ID" inputmode="numeric" value={centerId} onChange={(e) => setCenterId(e.target.value)}
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
