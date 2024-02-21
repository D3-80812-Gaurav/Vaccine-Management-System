import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function CenterHeader() {
    const [centerId, setCenterId] = useState();
    const [centerName, setCenterName] = useState("");
    const [stock, setStock] = useState(0);
    const navigate = useNavigate();

    const handleLogoutButton = () => {
        sessionStorage.removeItem("centerId");
        navigate("/home");
    }

    useEffect(() => {
        const centerId = sessionStorage.getItem("centerId");

        let data = JSON.stringify(centerId);
        let url = "http://localhost:8080/center/" + centerId;
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: url,
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                const centerDetails = (response.data);
                setCenterId(centerDetails.id);
                setCenterName(centerDetails.name);
                setStock(centerDetails.stock);
                console.log(centerDetails.id);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);
    return (
        <div class="card">
            <div class="card-body">
                <div class="container text-center">
                    <div class="row align-items-start">
                        <div class="col">
                            Center ID: {centerId}
                        </div>
                        <div class="col">
                            Center Name: {centerName}
                        </div>
                        <div class="col">
                            Available Vaccine Stock: {stock}
                        </div>
                        <div class="col">
                            <button className='btn btn-primary btn-sm' onClick={handleLogoutButton}>Logout</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
