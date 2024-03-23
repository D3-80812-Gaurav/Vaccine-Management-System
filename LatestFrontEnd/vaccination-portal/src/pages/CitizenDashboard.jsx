import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function CitizenDashboard(props) {
    const navigate = useNavigate();
    const baseURL = process.env.REACT_APP_API_URL;
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [aadharID, setAadharID] = useState();
    const [hasBookedFirstDose, setHasBookedFirstDose] = useState(false);
    const [hasBookedSecondDose, setHasBookedSecondDose] = useState(false);
    const [hasReviewedFirstDose, setHasReviewedFirstDose] = useState(false);
    const [hasReviewedSecondDose, setHasReviewedSecondDose] = useState(false);
    const [hasTakenFirstDose, setHasTakenFirstDose] = useState(false);
    const [hasTakenSecondDose, setHasTakenSecondDose] = useState(false);

    const getCitizenDetails = (() => {
        const token = sessionStorage.getItem("vpToken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/citizen_dashboard',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                const data = response.data;
                setFirstName(data.firstName);
                setLastName(data.lastName);
                setAadharID(data.aadharId);
                setHasBookedFirstDose(data.hasBookedFirstDose);
                setHasBookedSecondDose(data.hasBookedSecondDose);
                setHasReviewedFirstDose(data.hasReviewedFirstDose);
                setHasReviewedSecondDose(data.hasReviewedSecondDose);
                setHasTakenFirstDose(data.hasTakenFirstDose);
                setHasTakenSecondDose(data.setHasTakenSecondDose);
            })
            .catch((error) => {
                console.log(error);
            });
    });

    const handleDownloadVaccinationCertificate = (() => {
        navigate("/download_certificate");
    })

    const handleViewAppointment = (() => {
        console.log("In Handle View Appointments");
        navigate("/appointment_details");
    });

    const handleBookSlot = (() => {
        navigate("/book_slot");
    })

    useEffect(() => {
        getCitizenDetails();
    }, [])


    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <h1 className="text-center pt-5">Welcome {firstName} {lastName}</h1>
                <div className="container text-center mt-2">
                    {(hasTakenFirstDose || hasTakenSecondDose) && <>< button className="btn btn-light my-2" onClick={handleDownloadVaccinationCertificate}> Download Certificate</button><br></br></>}
                    {(hasBookedSecondDose || hasBookedFirstDose) && <>< button className="btn btn-light my-2" onClick={handleViewAppointment}> View Appointments</button><br></br></>}
                    {!(hasTakenFirstDose || hasTakenSecondDose) && !(hasBookedSecondDose || hasBookedFirstDose) && <>< button className="btn btn-light my-2" onClick={handleBookSlot}>Book Slot</button><br></br></>}
                </div>
            </div >
        </>
    )
}
