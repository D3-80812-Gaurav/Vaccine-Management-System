import React from 'react'
import { Link } from 'react-router-dom/cjs/react-router-dom.min'

export default function Register() {
    return (
        <div className="container border rounded w-50 p-3 mt-5">
            <h1 className='text-center'>Citizen Services</h1>
            <div className='text-center'><button type="submit" className="btn btn-primary mt-2 me-2" ><Link to="CitizenServices/Register" style={{ color: "white", textDecoration: 'none' }}>New Citizen Registration</Link></button></div >
            <div className='text-center'><button type="submit" className="btn btn-primary mt-2 me-2" ><Link to="CitizenServices/Login" style={{ color: "white", textDecoration: 'none' }}>Citizen Login</Link></button></div >
        </div >
    )
}
