import React from 'react'
import Navbar from '../components/Navbar'
import { Link } from 'react-router-dom'

export default function AdminDashboard() {
    return (
        <>
            <Navbar></Navbar>
            <div className="container w-50">
                <h1 className="text-center pt-5 mb-5">Welcome Admin</h1>
                <div className="container text-center mt-2">
                    <Link to="/add_center">< button className="btn btn-light btn-lg my-2" >Add New Center</button><br></br></Link>
                    <Link to="/register_aadhar">< button className="btn btn-light btn-lg my-2" >Register New Aadhar</button><br></br></Link>
                    <Link>< button className="btn btn-light my-2 btn-lg" >Modify Center</button><br></br></Link>
                </div>
            </div >
        </>
    )
}
