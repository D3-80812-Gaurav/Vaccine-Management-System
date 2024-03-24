import React, { useState } from 'react'
import usersignin from '../images/adminlogin.svg';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

export default function AdminLogin() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [formErrors, setFormErrors] = useState("");
    const handleSubmit = (e) => {
        loginAdmin();
        e.preventDefault();
        setEmail("");
        setPassword("");
    }

    const loginAdmin = async () => {
        const baseURL = process.env.REACT_APP_API_URL;
        let data = JSON.stringify({ "email": email, "password": password });

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: baseURL + 'admin/signin',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                sessionStorage.setItem("vpAtoken", response.data);
                toast.success("Login Successful");
                navigate("/admin_dashboard");
            })
            .catch((error) => {
                console.log(error);
                toast.warn("Invalid Credentials");
            });
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
                                        <label htmlFor="email" className="form-label">Admin Email:</label>
                                        <input type="email" className="form-control" id="email" placeholder="Enter Admin email" value={email} onChange={(e) => setEmail(e.target.value)}
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
