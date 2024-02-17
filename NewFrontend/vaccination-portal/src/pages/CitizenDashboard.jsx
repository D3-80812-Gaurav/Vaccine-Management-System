import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';

export default function CitizenDashboard(props) {
    console.log(sessionStorage.getItem("userDetails"))
    const data = JSON.parse(sessionStorage.getItem("userDetails"));
    console.log(data);
    const firstName = data.firstName;
    const lastName = data.lastName;
    const aadharID = data.aadharID;
    const hasBookedFirstDose = data.hasBookedFirstDose;
    const hasBookedSecondDose = data.hasBookedSecondDose
    const hasReviewedFirstDose = data.hasReviewedFirstDose;
    const hasReviewedSecondDose = data.hasReviewedSecondDose;
    const hasTakenFirstDose = data.hasTakenFirstDose;
    const hasTakenSecondDose = data.hasTakenSecondDose;

    //const doseNo =

    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container-fluid">
                <h1 class="text-center pt-5">Welcome {firstName} {lastName}</h1>
                <div className="container w-50">
                    {(hasTakenFirstDose || hasTakenSecondDose) && <>< button className="btn btn-light my-2"> Download Certificate</button><br></br></>}
                    {(hasBookedSecondDose || hasBookedFirstDose) && <>< button className="btn btn-light my-2"> View Appointments</button><br></br></>}
                    {(!hasTakenFirstDose || !hasTakenSecondDose) && <>< button className="btn btn-light my-2">Book Slot</button><br></br></>}
                </div>
            </div >
        </>
    )
}
