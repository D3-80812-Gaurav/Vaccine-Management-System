import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function CenterHeader() {
    const [centerId, setCenterId] = useState();
    const [centerName, setCenterName] = useState("");
    const [stock, setStock] = useState(0);
    const navigate = useNavigate();

    const getCenterDetails = () => {
        const cToken = sessionStorage.getItem("vpCtoken");
        const baseURL = process.env.REACT_APP_API_URL;
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'center/center_dashboard',
            headers: {
                'Authorization': 'Bearer ' + cToken
            }
        };

        axios.request(config)
            .then((response) => {
                const data = response.data
                setCenterId(data.id);
                setCenterName(data.name);
                setStock(data.stock);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    const handleLogoutButton = () => {
        sessionStorage.removeItem("vpCtoken");
        navigate("/home");
    }

    useEffect(() => {
        getCenterDetails();
    }, []);
    return (
        <div className="card">
            <div className="card-body">
                <div className="container text-center">
                    <div className="row align-items-start">
                        <div className="col">
                            Center ID: {centerId}
                        </div>
                        <div className="col">
                            Center Name: {centerName}
                        </div>
                        <div className="col">
                            Available Vaccine Stock: {stock}
                        </div>
                        <div className="col">
                            <button className='btn btn-primary btn-sm' onClick={handleLogoutButton}>Logout</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
