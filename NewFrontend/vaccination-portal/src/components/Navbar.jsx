import React from 'react'
import logo from '../images/logo.png'
import { Link } from 'react-router-dom'

export default function Navbar() {
    return (
        <div className="container-fluid d-flex flex-column p-0">
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <Link to="/home" style={{ color: "black", textDecoration: 'none' }}>
                        <div className="ms-3"><img src={logo} alt="Logo" width="24" height="24" className="d-inline-block align-text-top" /></div>
                    </Link>
                    <Link to="/home" style={{ color: "black", textDecoration: 'none' }}>
                        <span className="navbar-brand mb-0 h1">&nbsp;&nbsp;&nbsp;Cowin Vaccination Portal</span>
                    </Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/citizen_services" style={{ color: "black", textDecoration: 'none' }}>Citizen Services</Link></button>
                            </li>
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/faq" style={{ color: "black", textDecoration: 'none' }}>FAQs</Link></button>
                            </li>
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/admin_services" style={{ color: "black", textDecoration: 'none' }}>Admin Services</Link></button>
                            </li>
                            <li className="nav-item">
                                <button className="btn btn-light me-2" ><Link to="/center" style={{ color: "black", textDecoration: 'none' }}>Center Services</Link></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}
