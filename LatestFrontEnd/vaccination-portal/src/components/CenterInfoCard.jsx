import axios from 'axios';
import React from 'react'
import { useNavigate } from 'react-router-dom';

export default function CenterInfoCard(props) {
    const navigate = useNavigate();
    const { id, name, state, district, city, pincode, stock } = JSON.parse(props.info);
    const centerData = JSON.parse(props.info);
    const handleBookingAction = () => {
        const data = (JSON.parse(sessionStorage.getItem("userDetails")));
        sessionStorage.setItem("centerDetails", JSON.parse(JSON.stringify(props.info)));
        navigate("/book");
    }
    return (
        <>
            <div className="container w-50">
                <div className="container mt-2  ">
                    <div class="card" >
                        <div class="card-body">
                            <h5 class="card-title">{name}</h5>
                            <h6 class="card-subtitle mb-2 text-body-secondary">{city + " " + district + ", " + state + ", " + pincode}</h6>
                            <button className='btn btn-info' onClick={handleBookingAction}>Book</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}