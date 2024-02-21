import React, { useState } from 'react'
import Navbar from '../components/Navbar';
import SubNavbar from '../components/SubNavbar';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';

export default function BookingWindow(props) {
    const navigate = useNavigate();
    console.log(props);
    const center = JSON.parse((sessionStorage.getItem("centerDetails")));
    const user = JSON.parse((sessionStorage.getItem("userDetails")));
    const url = "http://localhost:8080/citizen/centers/book";
    //console.log(user.aadharID);

    const [date, setDate] = useState("");
    const handleSubmitHandleAction = (e) => {
        e.preventDefault();
        let data = {
            centerId: center.id,
            aadharId: user.aadharID,
            date: date,
        }
        axios.post(url, data)
            .then((response) => {
                console.log("Booked Successfully");
                toast.success("Appointment Booked Successfully");
                navigate("/citizen_dashboard")
            })
            .catch((error) => {
                console.log("Unable to Book Appointment Cancel");
                toast.warn("Unable to Book Appointment");
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
                        <input class="form-control" type="text" value={center.id} aria-label="readonly input example" id='centerId' readonly hidden="true" />
                        <input class="form-control" type="text" value={user.aadharID} aria-label="readonly input example" id='aadharId' readonly hidden="true" />
                        <label for="centerName" class="form-label mx-2">Center Name</label>
                        <input class="form-control" type="text" value={center.name} aria-label="readonly input example" id='centerName' readonly disabled="true" />
                        <label for="centerAddress" class="form-label mx-2">Center Address</label>
                        <input class="form-control" type="text" value={center.city + ", " + center.district + ", " + center.state + ", " + center.pincode} aria-label="readonly input example" readonly id='centerAddress' disabled="true" />
                        <label for="date" class="form-label mx-2">Choose A Date</label>
                        <input type="date" aria-label="Last name" class="form-control" id='date' required value={date} onChange={handleDateChangeEvent} />
                        <button className='btn btn-primary mt-2' type='submit' onClick={handleSubmitHandleAction}>Book Slot</button>
                    </form>
                </div >
            </div >
        </>
    )
}
