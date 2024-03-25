import React from 'react'
import axios from 'axios';
export default function AppointmentCard(props) {
    const handleMarkAsVaccinated = () => {
        const vpCtoken = sessionStorage.getItem("vpCtoken");
        const baseURL = process.env.REACT_APP_API_URL;
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'center/mark_as_vaccinated/' + props.data.aadharId,
            headers: {
                'Authorization': 'Bearer ' + vpCtoken
            }
        };

        axios.request(config)
            .then((response) => {
                window.location.reload();
            })
            .catch((error) => {
                console.log(error);
            });
    }
    const handleCancelAppointment = async () => {
        const vpCtoken = sessionStorage.getItem("vpCtoken");
        const baseURL = process.env.REACT_APP_API_URL;
        let config = {
            method: 'delete',
            maxBodyLength: Infinity,
            url: baseURL + 'center/appointment/cancel/' + props.data.aadharId,
            headers: {
                'Authorization': 'Bearer ' + vpCtoken
            }
        };

        axios.request(config)
            .then((response) => {
                window.location.reload();
            })
            .catch((error) => {
                console.log(error);
            });
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
