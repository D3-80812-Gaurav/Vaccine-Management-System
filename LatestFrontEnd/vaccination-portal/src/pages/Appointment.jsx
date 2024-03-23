import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { json, useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import SubNavbar from '../components/SubNavbar';
import { toast } from 'react-toastify';



export default function Appointment() {
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [aadharID, setAadharId] = useState();
    const [gender, setGender] = useState("");
    const [centerName, setCenterName] = useState("");
    const [centerAddress, setCenterAddress] = useState("");
    const [date, setDate] = useState("");

    const baseURL = process.env.REACT_APP_API_URL;

    const getAppointmentDetails = async () => {
        const token = sessionStorage.getItem("vpToken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/appointment_details',
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
                setAadharId(data.aadharId);
                setGender(data.gender);
                setCenterName(data.centerName);
                setCenterAddress(data.centerAddress);
                setDate(data.date);

            })
            .catch((error) => {
                console.log(error);
            });
    };

    useEffect(() => {
        getAppointmentDetails();
    }, [])

    const handlePrintAppointment = () => {
        var printContents = document.getElementById("print-table").innerHTML;
        var originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }
    const handleCancelAppointment = async () => {
        const token = sessionStorage.getItem("vpToken");
        let config = {
            method: 'delete',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/appointment/cancel',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                toast.success("Appointment Cancelled Successfully");
                navigate("/citizen_dashboard");
            })
            .catch((error) => {
                console.log(error);
            });
    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container mt-5 w-50">
                <div id='print-table'>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <td colSpan={2}>
                                    <div className="container text-center h1" >
                                        <strong>
                                            Appointment Details
                                        </strong>
                                    </div>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Name:</td>
                                <td>{firstName + " " + lastName}</td>
                            </tr>
                            <tr>
                                <td>Aadhar Card ID:</td>
                                <td>{aadharID}</td>
                            </tr>
                            <tr>
                                <td>Gender:</td>
                                <td>{gender}</td>
                            </tr>
                            <tr>
                                <td>Center:</td>
                                <td>{centerName}</td>
                            </tr>
                            <tr>
                                <td>Center Address:</td>
                                <td>{centerAddress}</td>
                            </tr>
                            <tr>
                                <td>Appointment Date:</td>
                                <td>{date}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="container text-center mt-2">
                    <button className='btn btn-primary text-center me-1' onClick={handlePrintAppointment}>Print Appointment Details</button>
                    <button className='btn btn-danger text-center ms-1' onClick={handleCancelAppointment}>Cancel Appointment</button>
                </div>
            </div>
        </>
    )
}
