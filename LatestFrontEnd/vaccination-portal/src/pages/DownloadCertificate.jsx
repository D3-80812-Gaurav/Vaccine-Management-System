import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar'
import axios from 'axios';
import { toast } from 'react-toastify';

export default function DownloadCertificate() {
    const baseURL = process.env.REACT_APP_API_URL;
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [age, setAge] = useState();
    const [aadharID, setAadharID] = useState();
    const [gender, setGender] = useState();
    const [vaccinationStatus, setVaccinationStatus] = useState("");
    const [imageData, setImageData] = useState(null);

    function print() {
        let printContents = document.getElementById('printablediv').innerHTML;
        let originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }
    const getCertificate = (() => {
        const token = sessionStorage.getItem("vpToken");
        const baseURL = process.env.REACT_APP_API_URL;
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/download_certificate',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios.request(config)
            .then((response) => {
                toast.success("Successfully Received Data");
                const userData = response.data;
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
        const token = sessionStorage.getItem("vpToken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            responseType: 'blob',
            url: baseURL + 'citizen/generate_qr_code',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        async function makeRequest() {
            try {
                const response = await axios.request(config);
                setImageData(URL.createObjectURL(response.data));
            }
            catch (error) {
                console.log(error);
            }
        }

        makeRequest();
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
                        <table className="table ">
                            <tbody>
                                <tr>
                                    <td>Citizen Name :</td>
                                    <td>{firstName} {lastName}</td>
                                </tr>
                                <tr>
                                    <td>AadharID :</td>
                                    <td>{aadharID}</td>
                                </tr>
                                <tr>
                                    <td>Gender :</td>
                                    <td>{gender}</td>
                                </tr>
                                <tr>
                                    <td>Vaccination Status :</td>
                                    <td>{vaccinationStatus}</td>
                                </tr>
                                <tr>
                                    <td>Age :</td>
                                    <td>{age}</td>
                                </tr>
                            </tbody>
                        </table>
                        <div className='text-center'>{imageData && <img src={imageData} alt="API Image" width={150} />}</div>
                    </div>
                    <div className='text-center'>
                        <button className='btn btn-primary' onClick={print} >Print</button>
                    </div>
                </div>
            </div>
        </>
    )
}
