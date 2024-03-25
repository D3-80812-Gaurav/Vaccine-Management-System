import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import axios from 'axios';

export default function Stats() {
    const baseURL = process.env.REACT_APP_API_URL;
    const [totalVaccinations, setTotalVaccinations] = useState();
    const [totalMaleVaccinations, setTotalMaleVaccinations] = useState();
    const [totalFemaleVaccinations, setTotalFemaleVaccinations] = useState();
    const [totalAppointments, setTotalAppointments] = useState();
    useEffect(() => {
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/statistics',
            headers: {}
        };

        axios.request(config)
            .then((response) => {
                const data = response.data;
                setTotalVaccinations(data.totalVaccinations);
                setTotalMaleVaccinations(data.maleVaccinations);
                setTotalFemaleVaccinations(data.femaleVaccinations);
                setTotalAppointments(data.totalAppointments);
            })
            .catch((error) => {
                console.log(error);
            });
    })
    return (
        <>
            <Navbar></Navbar>
            <div className='container mt-5 w-50'>
                <div className='text-center'>
                    <div className="card  text-bg-light my-2 ">
                        <div className="card-body">
                            <h2>💉 Total Vaccinations : {totalVaccinations}</h2>
                        </div>
                    </div>
                    <div className="card  text-bg-light my-2 ">
                        <div className="card-body">
                            <h2>🧑 Total Males Vaccinated : {totalMaleVaccinations}</h2>
                        </div>
                    </div>
                    <div className="card  text-bg-light my-2 ">
                        <div className="card-body">
                            <h2>👩 Total Females Vaccinated : {totalFemaleVaccinations}</h2>
                        </div>
                    </div>
                    <div className="card  text-bg-light my-2 ">
                        <div className="card-body">
                            <h2>📅 Total Appointments : {totalAppointments}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
