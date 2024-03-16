import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import { toast } from 'react-toastify';
import { getWithoutAuth } from '../axiosInstance';

export default function Stats() {
    const [totalVaccinations, setTotalVaccinations] = useState();
    const [totalMaleVaccinations, setTotalMaleVaccinations] = useState();
    const [totalFemaleVaccinations, setTotalFemaleVaccinations] = useState();
    const [totalAppointments, setTotalAppointments] = useState();
    useEffect(() => {
        getWithoutAuth('citizen/statistics')
            .then((response) => {
                const data = (response.data);
                console.log(JSON.parse(data));
                setTotalVaccinations(data.totalVaccinations);
                setTotalAppointments(data.totalAppointments);
                setTotalMaleVaccinations(data.maleVaccinations);
                setTotalFemaleVaccinations(data.femaleVaccinations);
                toast.success("Fetching Data");
            })
            .catch((error) => {
                toast.warn("Unable To Fetch Data");
            });
    })
    return (
        <>
            <Navbar></Navbar>
            <div className='container mt-5 w-50'>
                <div className='text-center'>
                    <div class="card  text-bg-light my-2 ">
                        <div class="card-body">
                            <h2>💉 Total Vaccinations : {totalVaccinations}</h2>
                        </div>
                    </div>
                    <div class="card  text-bg-light my-2 ">
                        <div class="card-body">
                            <h2>🧑 Total Males Vaccinated : {totalMaleVaccinations}</h2>
                        </div>
                    </div>
                    <div class="card  text-bg-light my-2 ">
                        <div class="card-body">
                            <h2>👩 Total Females Vaccinated : {totalFemaleVaccinations}</h2>
                        </div>
                    </div>
                    <div class="card  text-bg-light my-2 ">
                        <div class="card-body">
                            <h2>📅 Total Appointments : {totalAppointments}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
