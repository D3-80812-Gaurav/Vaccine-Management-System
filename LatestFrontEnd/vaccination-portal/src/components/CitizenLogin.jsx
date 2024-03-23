import React, { useState } from 'react'
import usersignin from '../images/usersignin.svg';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';

export default function CitizenLogin() {
    const navigate = useNavigate();
    const baseURL = process.env.REACT_APP_API_URL;
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const handleSubmit = (e) => {
        e.preventDefault();
        loginCitizen();
        setEmail("");
        setPassword("");
    }
    const loginCitizen = async () => {
        let data = JSON.stringify({ "email": email, "password": password });
        console.log(data);
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/signin',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };
        axios.request(config)
            .then((response) => {
                sessionStorage.setItem("vpToken", response.data);
                toast.success("Login Successful");
                navigate('/citizen_dashboard');
            })
            .catch((error) => {
                toast.warn("Invalid Credentials");
                console.log('Response status:', error.response.status);
                console.log('Response headers:', error.response.headers);
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
                                        <img src={usersignin} style={{ width: 400 }} className="rounded mx-auto d-block my-2"></img>
                                        <label htmlFor="email" className="form-label">Email ID:</label>
                                        <input type="email" className="form-control" id="email" placeholder="Enter Email ID" value={email} onChange={(e) => setEmail(e.target.value)}
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
