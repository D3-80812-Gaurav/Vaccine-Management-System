import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar'
import axios from 'axios';
import { toast } from 'react-toastify';

export default function PrintAadharCardWindow() {
    const [id, setId] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [dob, setDob] = useState("");
    const [gender, setGender] = useState("");
    const [state, setState] = useState("");
    const [city, setCity] = useState("");
    const [pincode, setPincode] = useState("");
    const [aadharId, setAadharId] = useState("");
    const [imageData, setImageData] = useState(null);

    const getAadharDetails = () => {

        const baseURL = process.env.REACT_APP_API_URL;
        const vpAtoken = sessionStorage.getItem("vpAtoken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'admin/get_aadhar_card/' + aadharId,
            headers: {
                'Authorization': 'Bearer ' + vpAtoken
            }
        };

        axios.request(config)
            .then((response) => {
                console.log(response.data);
                const res = response.data;
                toast.success("Found Aadhar");
                setAadharId("");
                setId(res.id);
                setFirstName(res.firstName);
                setLastName(res.lastName);
                setDob(res.dob);
                setGender(res.gender);
                setCity(res.city);
                setState(res.state);
                setPincode(res.pinCode);
            })
            .catch((error) => {
                console.log(error);
                setAadharId("");
                setId("");
                setFirstName("");
                setLastName("");
                setDob("");
                setGender("");
                setCity("");
                setState("");
                setPincode("");
            });
    }
    function print() {
        let printContents = document.getElementById('printablediv').innerHTML;
        let originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }

    const getQRCode = () => {
        const baseURL = process.env.REACT_APP_API_URL;
        const token = sessionStorage.getItem("vpAtoken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            responseType: 'blob',
            url: baseURL + 'admin/get_aadhar_qr/' + aadharId,
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        async function makeRequest() {
            try {
                const response = await axios.request(config);
                console.log(response.data);
                setImageData(URL.createObjectURL(response.data));
            }
            catch (error) {
                console.log(error);
            }
        }

        makeRequest();
    }

    const handleSearch = () => {
        setAadharId();
        getAadharDetails();
        getQRCode();

    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <div className="container text-center mt-2  ">
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Enter Aadhar Card ID:" required value={aadharId} onChange={(e) => setAadharId(e.target.value)} />
                        <button class="btn btn-outline-success" type="button" onClick={handleSearch}>Search</button>
                    </form>
                </div>
                {(firstName.length == 0) &&
                    <div className="container text-center mt-2  text-danger">
                        <h3>{id}</h3>
                        <h5>Looks this Aadhar Does Not Exist Search 🔍 for Another AadharID</h5>
                    </div>}
                {(firstName.length != 0) &&
                    (
                        <>
                            <div id='printablediv'>
                                <div className="container border shadow  p-1 rounded mt-5">
                                    <div class="row g-0">
                                        <div class="col-md-4">
                                            <div className='text-center mt-2'>{imageData && <img src={imageData} alt="API Image" width={250} />}</div>
                                        </div>
                                        <div class="col-md-8">
                                            <div class="text center">
                                                <h4 class="ms-2 mt-3 fw-bolder">☀️&nbsp; Government Of Sunbeam 🔆</h4>
                                                <h6 class="fw-semibold ms-5">Unique Identifier Authority of Sunbeam</h6>
                                                <h5 class="card-text">Name: {firstName + " " + lastName}</h5>
                                                <h5 class="card-text">AadharID: {id}</h5>
                                                <h5 class="card-text">DOB: {dob}</h5>
                                                <h5 class="text">Gender: {gender}</h5>
                                                <h5 class="text">Address: {city + ", " + state + ", Pincode-" + pincode}</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className='text-center mt-3'>
                                <button className='btn btn-secondary' onClick={print} >Print Aadhar Card</button>
                            </div>
                        </>
                    )}
            </div >
        </>
    )
}
