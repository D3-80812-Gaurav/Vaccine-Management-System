import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function CitizenDashboard(props) {
    const navigate = useNavigate();
    const ur = "http://localhost:8080/citizen/citizen_dashboard";

    console.log(sessionStorage.getItem("userDetails"));
    const data = JSON.parse(sessionStorage.getItem("userDetails"));
    console.log(data);
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
        const data = JSON.parse(sessionStorage.getItem("userDetails"));
        const aadharID = data.aadharID;
        axios.get(ur.concat("/" + aadharID)).then((response) => {
            console.log(JSON.stringify(response.data));
            const stringifiedData = JSON.stringify(response.data);
            const jdata = JSON.parse(stringifiedData);
            setFirstName(jdata.firstName);
            setLastName(jdata.lastName);
            setAadharID(jdata.aadharId);
            setHasBookedFirstDose(jdata.hasBookedFirstDose);
            setHasBookedSecondDose(jdata.hasBookedSecondDose);
            setHasReviewedFirstDose(jdata.hasReviewedFirstDose);
            setHasReviewedSecondDose(jdata.hasReviewedSecondDose);
            setHasTakenFirstDose(jdata.hasTakenFirstDose);
            setHasTakenSecondDose(jdata.setHasTakenSecondDose);
        }).catch((error) => {
            console.log(error);
        })
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
                    {!(hasBookedSecondDose || hasBookedFirstDose) && <>< button className="btn btn-light my-2" onClick={handleBookSlot}>Book Slot</button><br></br></>}
                </div>
            </div >
        </>
    )
}
