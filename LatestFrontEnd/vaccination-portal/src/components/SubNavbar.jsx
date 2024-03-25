import React from 'react'
import { useNavigate } from 'react-router-dom';

export default function SubNavbar() {
    const navigate = useNavigate();
    const handleLogoutAction = () => {
        sessionStorage.removeItem("vpToken");
        sessionStorage.removeItem("vpAtoken");
        sessionStorage.removeItem("vpCtoken");
        navigate("/home");
    }
    return (
        <nav className="navbar navbar-expand-lg bg-white">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <button className='btn btn-secondary' onClick={handleLogoutAction}>Logout</button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    )
}
