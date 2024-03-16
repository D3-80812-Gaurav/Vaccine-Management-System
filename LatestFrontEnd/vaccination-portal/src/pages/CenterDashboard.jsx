import React, { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import axios from 'axios';
import CenterHeader from '../components/CenterHeader';
import Appointments from '../components/Appointments';

export default function CenterDashboard() {
    const [upcomingAppointments, setUpcomingAppointments] = useState([]);
    const url_get_appointments = "http://localhost:8080/center/appointments";
    const centerId = sessionStorage.getItem("centerId");
    console.log(centerId);
    useEffect(() => {
        axios.get(url_get_appointments.concat("/" + centerId), { data: Number.parseInt(centerId) })
            .then((response) => {
                setUpcomingAppointments(JSON.stringify(response.data));
                console.log(upcomingAppointments);
            })
            .catch((error) => {
                console.log("Unable to Cancel");
                //toast.warn("An Error Occurred");
            });
    }, [])
    return (
        <>
            <Navbar />
            <CenterHeader />
            {/* <div class="container text-center my-2">
                {upcomingAppointments.length === 0 && <h3>You Have No Upcoming Appointments</h3>}
            </div> */}
            <div>
                <Appointments />
            </div>
        </>
    )
}
