import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Footer from '../components/Footer';

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

    const canViewAppointments = () => {
        if (hasTakenFirstDose && hasTakenSecondDose)
            return false;
        else if (hasBookedFirstDose || hasBookedSecondDose)
            return true;
        else
            return false;
    }

    const canBook = (() => {
        if (hasTakenFirstDose && hasTakenSecondDose) {
            return false;
        }
        else if ((hasTakenFirstDose && !hasTakenSecondDose) && !hasBookedSecondDose) {
            return true;
        }
        else if (!hasTakenFirstDose && !hasTakenSecondDose)
            return true;
        else
            return false;
    })

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
                const data = response.data;
                setFirstName(data.firstName);
                setLastName(data.lastName);
                setAadharID(data.aadharId);
                setHasBookedFirstDose(data.hasBookedFirstDose);
                setHasBookedSecondDose(data.hasBookedSecondDose);
                setHasReviewedFirstDose(data.hasReviewedFirstDose);
                setHasReviewedSecondDose(data.hasReviewedSecondDose);
                setHasTakenFirstDose(data.hasTakenFirstDose);
                setHasTakenSecondDose(data.hasTakenSecondDose);
            })
            .catch((error) => {
                console.log(error);
            });
    });

    const handleDownloadVaccinationCertificate = (() => {
        navigate("/download_certificate");
    })

    const handleViewAppointment = (() => {
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
                <h1 className="text-center pt-5 fw-bolder pb-2">Welcome {firstName} {lastName}</h1>
                <div className="container text-center mt-2">
                    {(hasTakenFirstDose && hasTakenSecondDose) ? <h6 className='text-success-emphasis fw-bold mb-3'>Congratulations! You've completed your COVID-19 vaccination. Download your certificate now to safeguard yourself and others. Thank you for your commitment. Stay safe!</h6> : <></>}
                    {(hasTakenFirstDose && !hasTakenSecondDose) ? <h6 className='text-success-emphasis fw-bold mb-3'>Congrats on getting your first COVID-19 vaccine dose! Download your certificate below</h6> : <></>}
                    {(hasTakenFirstDose || hasTakenSecondDose) && <>< button className="btn btn-light my-2" onClick={handleDownloadVaccinationCertificate}> Download Certificate</button><br></br></>}
                    {(canBook() === true) ? <>< button className="btn btn-light my-2" onClick={handleBookSlot}>Book Slot</button><br></br></> : <></>}
                    {(canViewAppointments() == true) ? <>< button className="btn btn-light my-2" onClick={handleViewAppointment}> View Appointments</button><br></br></> : <></>}
                </div>
            </div >
            <Footer></Footer>
        </>
    )
}
