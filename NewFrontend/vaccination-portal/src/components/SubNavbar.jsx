import React from 'react'
import { useNavigate } from 'react-router-dom';

export default function SubNavbar() {
    const navigate = useNavigate();
    const handleLogoutAction = () => {
        sessionStorage.removeItem("userDetails");
        navigate("/home");
    }
    return (
        <nav class="navbar navbar-expand-lg bg-white">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <button className='btn btn-secondary' onClick={handleLogoutAction}>Logout</button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    )
}
