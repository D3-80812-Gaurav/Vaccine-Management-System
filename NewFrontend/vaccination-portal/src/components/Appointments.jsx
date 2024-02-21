import React, { useEffect, useState } from 'react'
import axios from 'axios';
import AppointmentCard from './AppointmentCard';
import { toast } from 'react-toastify';
export default function Appointments() {
    const [upcomingAppointments, setUpcomingAppointments] = useState([]);
    const centerId = sessionStorage.getItem("centerId");
    console.log(centerId);
    useEffect(() => {

        //const axios = require('axios');

        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: 'http://localhost:8080/center/appointments/' + centerId,
            headers: {}
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                setUpcomingAppointments([]);
                console.log(JSON.stringify(response.data));
                console.log(response.data);
                const stringifiedData = JSON.stringify(response.data);
                const jdata = JSON.parse(stringifiedData);
                jdata.forEach((item, index) => {
                    console.log(index);
                    console.log(JSON.stringify(item));
                    console.log("Hi");
                    setUpcomingAppointments(upcomingAppointments => [...upcomingAppointments, JSON.stringify(item)]);
                });
            })
            .catch((error) => {
                console.log(error);
                toast.warn("Unable to get Appointment");
            });
    }, []);
    return (
        <>
            <> < div className='container text-center'>
                <table class="table table-borderless">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Aadhar ID</th>
                            <th scope="col">Mark As Vaccinated</th>
                            <th scope="col">Cancel Appointment</th>
                        </tr>
                    </thead>
                    <tbody>
                        {upcomingAppointments.map((app) => {
                            console.log("Hello");
                            return <>
                                <tr>
                                    <AppointmentCard data={JSON.parse(app)} />
                                </tr>
                            </>
                        }
                        )}
                    </tbody>
                </table>
            </div >
            </>
        </>
    )
}
