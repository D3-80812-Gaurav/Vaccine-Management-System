import React, { useState } from 'react'
import Navbar from '../components/Navbar';
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';
import CenterInfoCard from '../components/CenterInfoCard';

export default function BookSlot() {
    const [pinCode, setPinCode] = useState();
    const ur = "http://localhost:8080/citizen/centers";
    const [centers, setCenters] = useState([]);

    const handlePinCodeChnage = (e) => {
        setPinCode(e.target.value);
    }
    const handleSearch = () => {
        setCenters([]);
        axios.get(ur.concat("/" + pinCode)).then((response) => {
            console.log(1);
            console.log(JSON.stringify(response.data));
            const stringifiedData = JSON.stringify(response.data);
            console.log(2);
            console.log(stringifiedData);
            const jdata = JSON.parse(stringifiedData);
            console.log(typeof (jdata));
            jdata.forEach((item, index) => {
                console.log(index);
                console.log(JSON.stringify(item));
                setCenters(centers => [...centers, JSON.stringify(item)]);
            });
        }).catch((error) => {
            console.log(error);
        })
    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <div className="container text-center mt-2  ">
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Search Your Nearest Center By Entering Your PINCODE" required value={pinCode} onChange={handlePinCodeChnage} min={100000} max={999999} inputMode='numeric' />
                        <button class="btn btn-outline-success" type="button" onClick={handleSearch}>Search</button>
                    </form>
                </div>
                {centers.length === 0 &&
                    <div className="container text-center mt-2  text-danger">
                        <h5>Looks like there are no centers near you please search 🔍 for another pincode</h5>
                    </div>}
            </div>
            {centers.map((center) => {
                return <CenterInfoCard info={center}></CenterInfoCard>
            }
            )}
        </>
    )
}
