import React from 'react'
import { Link } from 'react-router-dom'

export default function WelcomeAndLinks() {
    return (
        <>
            <div className="container-fluid mt-3">
                <div class="p-3 mb-2 bg-body-tertiary border rounded-3">
                    <div class="container-fluid py-2">
                        <h1 class="display-5 fw-bold">Welcome to COVID Vaccination Portal</h1>
                        <p class="fs-4">Empowering Communities Through Vaccination
                            Join us in the fight against COVID-19 by getting vaccinated today. Our platform provides a seamless and efficient way for you to schedule your vaccination appointment, ensuring you and your loved ones stay protected.<br />Together, let's build immunity and bring an end to this pandemic. Book your vaccination appointment now and take a step towards a healthier future.
                        </p>
                        <div>
                            <button class="btn btn-info btn-lg me-2" type="button"><Link to="/citizen_services" style={{ color: "black", textDecoration: 'none' }}>Citizen Login</Link></button>
                            <button class="btn btn-info btn-lg me-2" type="button">New Citizen Registration</button>
                            <button class="btn btn-info btn-lg me-2" type="button"><Link to="/center" style={{ color: "black", textDecoration: 'none' }}>Center Login</Link></button>
                            <button class="btn btn-info btn-lg me-2" type="button"><Link to="/admin_services" style={{ color: "black", textDecoration: 'none' }}>Admin Login</Link></button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
