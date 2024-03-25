import React, { useEffect, useState } from 'react'
import Navbar from '../components/Navbar';
import SubNavbar from '../components/SubNavbar';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';

export default function BookingWindow(props) {
    const navigate = useNavigate();
    let { id } = useParams();
    const baseURL = process.env.REACT_APP_API_URL;
    const [date, setDate] = useState("");
    const [centerId, setCenterId] = useState("");
    const [centerName, setCenterName] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [pincode, setPincode] = useState("");

    useEffect(() => {
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'center/find/' + id,
            headers: {}
        };

        axios.request(config)
            .then((response) => {
                const data = response.data;
                setCenterId(data.id);
                setCenterName(data.name);
                setCity(data.city);
                setState(data.state);
                setPincode(data.pincode);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [])

    const handleSubmitHandleAction = (e) => {
        e.preventDefault();
        bookAppointment();
        navigate("/citizen_dashboard");
    }

    const bookAppointment = () => {
        const baseURL = process.env.REACT_APP_API_URL;
        let data = JSON.stringify({ "centerId": centerId, "date": date });
        const token = sessionStorage.getItem("vpToken");
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: baseURL + 'citizen/centers/book',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                toast.success("Booked Appointment Successfully");
            })
            .catch((error) => {
                toast.warn("Unable to Book Apppointment");
            });
    }

    const handleDateChangeEvent = (event) => {
        setDate(event.target.value);
        //Date.parse()
    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div>
                <div className="container w-50">
                    <form>
                        <input className="form-control" type="text" value={centerId} aria-label="readonly input example" id='centerId' readonly hidden="true" />
                        <input className="form-control" type="text" value={123} aria-label="readonly input example" id='aadharId' readonly hidden="true" />
                        <label for="centerName" className="form-label mx-2">Center Name</label>
                        <input className="form-control" type="text" value={centerName} aria-label="readonly input example" id='centerName' readonly disabled="true" />
                        <label for="centerAddress" className="form-label mx-2">Center Address</label>
                        <input className="form-control" type="text" value={city + ", " + state + ", " + pincode} aria-label="readonly input example" readonly id='centerAddress' disabled="true" />
                        <label for="date" className="form-label mx-2">Choose A Date</label>
                        <input type="date" aria-label="Last name" className="form-control" id='date' required value={date} onChange={handleDateChangeEvent} />
                        <button className='btn btn-primary mt-2' type='submit' onClick={handleSubmitHandleAction}>Book Slot</button>
                    </form>
                </div >
            </div >
        </>
    )
}
