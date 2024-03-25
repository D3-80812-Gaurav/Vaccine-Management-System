import React, { useState } from 'react'
import axios from 'axios';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import Footer from './Footer';

export default function AdminLogin() {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
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
            <div className="container container-fluid mt-5">
                <div className="container">
                    <div className="row">
                        <div className="col">
                        </div>
                        <div className="col">
                            <div className="container border rounded mt-4 shadow p-3">
                                <form action="submit">
                                    <div className="mb-3">
                                        <div className="text-center mt-2 fw-bolder"><h1>Admin Sign In</h1></div>
                                        <label htmlFor="email" className="form-label">Email ID:</label>
                                        <input type="email" className="form-control" id="email" placeholder="example@example.com" value={email} onChange={(e) => setEmail(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="password" className="form-label">Password:</label>
                                        <input type="password" className="form-control" id="password" placeholder="Enter Your Password" value={password} onChange={(e) => setPassword(e.target.value)}
                                            required />
                                    </div>
                                    <div className="mb-3">
                                        <div className="d-grid gap-2 text-center">
                                            <button type="submit" className="btn btn-primary mt-2" onClick={handleSubmit}>Submit</button>
                                            <h6 className='mt-2'>Not Admin? Go to <Link to="/citizen_services">Citizen Login</Link></h6>
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
