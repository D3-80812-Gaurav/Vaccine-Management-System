import React from 'react'
import { Link } from 'react-router-dom';
import logo from './../images/logo.png'

export default function Navbar(props) {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top">
                <div className="ms-3"><img src={logo} alt="Logo" width="24" height="24" class="d-inline-block align-text-top" /></div>
                <span className="navbar-brand mb-0 h1">&nbsp;&nbsp;&nbsp;Cowin Vaccination Portal</span>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <button className="btn btn-light me-2" ><Link to="/home" style={{ color: "black", textDecoration: 'none' }}>Home</Link></button>
                        </li>
                        <li className="nav-item">
                            <button className="btn btn-light me-2" ><Link to="/CitizenServices" style={{ color: "black", textDecoration: 'none' }}>Citizen Services</Link></button>
                        </li>
                        <li className="nav-item">
                            <button className="btn btn-light me-2" ><Link to="/FAQ" style={{ color: "black", textDecoration: 'none' }}>FAQs</Link></button>
                        </li>
                        <li className="nav-item">
                            <button className="btn btn-light me-2" ><Link to="/AdminServices" style={{ color: "black", textDecoration: 'none' }}>Admin Services</Link></button>
                        </li>
                    </ul>
                </div>
            </nav>
        </>
    )
}
