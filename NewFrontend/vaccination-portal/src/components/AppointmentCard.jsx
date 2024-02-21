import React from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export default function AppointmentCard(props) {
    const navigate = useNavigate();
    const handleMarkAsVaccinated = () => {
        const centerId = sessionStorage.getItem("centerId");
        let data = JSON.stringify({
            "aadharCardId": props.data.aadharId,
            "centerId": centerId
        });

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:8080/center/mark_as_vaccinated',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                navigate("/center_dashboard");
            })
            .catch((error) => {
                console.log(error);
            });

    }
    const handleCancelAppointment = () => {

    }
    return (
        <>
            <td>{props.data.firstName} {props.data.lastName}</td>
            <td>{props.data.aadharId} </td>
            <td><button className='btn btn-success' onClick={handleMarkAsVaccinated}> Mark as Vaccinated</button></td>
            <td><button className='btn btn-danger' onClick={handleCancelAppointment}> Cancel Appointment</button></td>
        </>
    )
}
