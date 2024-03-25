import React, { useEffect, useState } from 'react'
import axios from 'axios';
import AppointmentCard from './AppointmentCard';
export default function Appointments() {
    const [upcomingAppointments, setUpcomingAppointments] = useState([]);
    useEffect(() => {
        const baseURL = process.env.REACT_APP_API_URL;
        const vpCtoken = sessionStorage.getItem("vpCtoken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'center/appointments',
            headers: {
                'Authorization': 'Bearer ' + vpCtoken
            }
        };

        axios.request(config)
            .then((response) => {
                setUpcomingAppointments([]);
                const data = response.data;
                data.forEach((item) => {
                    setUpcomingAppointments(upcomingAppointments => [...upcomingAppointments, JSON.stringify(item)]);
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);
    return (
        <>
            <>
                {upcomingAppointments.length == 0 ?
                    < h3 className='container text-center mt-2'>
                        <h2>You Have No Upcoming Appointments</h2>
                    </h3>
                    : < div className='container text-center'>
                        <table className="table table-borderless">
                            <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Aadhar ID</th>
                                    <th scope="col">Mark As Vaccinated</th>
                                    <th scope="col">Cancel Appointment</th>
                                </tr>
                            </thead>
                            <tbody>
                                {upcomingAppointments.map((app, index) => {
                                    return <>
                                        <tr>
                                            <AppointmentCard data={JSON.parse(app)} />
                                        </tr>
                                    </>
                                }
                                )}
                            </tbody>
                        </table>
                    </div >}
            </>
        </>
    )
}
