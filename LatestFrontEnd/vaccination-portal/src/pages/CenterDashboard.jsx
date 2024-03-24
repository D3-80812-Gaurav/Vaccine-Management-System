import React, { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import axios from 'axios';
import CenterHeader from '../components/CenterHeader';
import Appointments from '../components/Appointments';

export default function CenterDashboard() {
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
