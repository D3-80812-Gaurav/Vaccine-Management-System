import React from 'react'
import logo from '../images/logo.png'
import { Link } from 'react-router-dom'

const userType = () => {
    const userToken = sessionStorage.getItem("vpToken");
    const adminToken = sessionStorage.getItem("vpAtoken");
    const centerToken = sessionStorage.getItem("vpCtoken");
    if (userToken !== null && adminToken === null && centerToken === null)
        return "user";
    else if (userToken === null && adminToken === null && centerToken !== null)
        return "center";
    else if (userToken === null && adminToken !== null && centerToken === null)
        return "admin";
    else
        return "";
}

export default function Navbar() {
    return (
        <div className="container-fluid d-flex flex-column p-0">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <Link to="/home" style={{ color: "black", textDecoration: 'none' }}>
                        <div className="ms-3"><img src={logo} alt="Logo" width="24" height="24" className="d-inline-block align-text-top" /></div>
                    </Link>
                    <Link to="/home" style={{ color: "black", textDecoration: 'none' }}>
                        <span className="navbar-brand mb-0 h1">&nbsp;&nbsp;&nbsp;Cowin Vaccination Portal</span>
                    </Link>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/faq" style={{ color: "black", textDecoration: 'none' }}>FAQs</Link></button>
                            </li>
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/stats" style={{ color: "black", textDecoration: 'none' }}>Statistics</Link></button>
                            </li>
                            {userType() === "user" ? (<li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/citizen_dashboard" style={{ color: "black", textDecoration: 'none' }}>Citizen Dashboard</Link></button>
                            </li>) : <></>}
                            {userType() === "center" ? (<li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/center" style={{ color: "black", textDecoration: 'none' }}>Center Dashboard</Link></button>
                            </li>) : <></>}
                            {userType() === "admin" ? (<li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/admin_dashboard" style={{ color: "black", textDecoration: 'none' }}>Admin Dashboard</Link></button>
                            </li>) : <></>}
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}
