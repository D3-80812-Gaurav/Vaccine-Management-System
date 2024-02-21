import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar'
import moment from 'moment';
import axios from 'axios';
import { toast } from 'react-toastify';

export default function DownloadCertificate() {
    const user = JSON.parse(sessionStorage.getItem("userDetails"));
    console.log(user.citizenID);
    const url = "http://localhost:8080/citizen/download_certificate";
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState();
    const [aadharID, setAadharID] = useState();
    const [gender, setGender] = useState();
    const [vaccinationStatus, setVaccinationStatus] = useState("");
    const [imageData, setImageData] = useState('');

    function print() {
        let printContents = document.getElementById('printablediv').innerHTML;
        let originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }
    const getCertificate = (() => {
        let data = JSON.stringify(user.aadharID);

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:8080/citizen/download_certificate',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                toast.success("Successfully Received Data");
                const userData = JSON.parse(JSON.stringify(response.data))
                setFirstName(userData.firstName);
                setLastName(userData.lastName);
                setAge(userData.age);
                setAadharID(userData.aadharId);
                setGender(userData.gender);
                setVaccinationStatus(userData.vaccinationStatus);
            })
            .catch((error) => {
                console.log(error);
            });
    })

    const getQRCode = (() => {
        //complete it later
    })
    useEffect(() => {
        getCertificate();
        getQRCode();
    }, [])
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className='container mb-5'>
                <div className="container border rounded w-50 p-3 mt-2">
                    <div id='printablediv'>
                        <h1 className='text-center'>Covid Vaccination Certificate</h1>
                        <table class="table">
                            <tbody>
                                <tr><u><th className='text-decoration-underline p-2' colSpan={2}>Citizen Details</th></u></tr>
                                <tr>
                                    <td>Citizen Name :</td>
                                    <td>{firstName} {lastName}</td>
                                </tr>
                                <tr>
                                    <td>AadharID :</td>
                                    <td>{aadharID}</td>
                                </tr>
                                <tr>
                                    <td>Vaccination Status :</td>
                                    <td>{vaccinationStatus}</td>
                                </tr>
                                <tr>
                                    <td>Age :</td>
                                    <td>{age}</td>
                                </tr>
                                <tr id='QR'>
                                    <td><div>
                                        {imageData && (
                                            <img src={`data:image/png;base64,${imageData}`} alt="QR Code" />
                                        )}
                                    </div></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div className='text-center'>
                        <button className='btn btn-primary' onClick={print} >Print</button>
                    </div>
                </div>
            </div>
        </>
    )
}
