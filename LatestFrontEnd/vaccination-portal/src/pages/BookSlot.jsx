import React, { useState } from 'react'
import Navbar from '../components/Navbar';
import SubNavbar from '../components/SubNavbar';
import axios from 'axios';
import CenterInfoCard from '../components/CenterInfoCard';

export default function BookSlot() {
    const [pinCode, setPinCode] = useState();
    const baseURL = process.env.REACT_APP_API_URL;
    const [centers, setCenters] = useState([]);

    const handlePinCodeChnage = (e) => {
        setPinCode(e.target.value);
    }
    const handleSearch = () => {
        setCenters([]);

        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'center/centers/' + pinCode,
            headers: {}
        };

        axios.request(config)
            .then((response) => {
                const data = response.data;
                data.forEach((item, index) => {
                    console.log(index);
                    setCenters(centers => [...centers, JSON.stringify(item)]);
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <div className="container text-center mt-2  ">
                    <form className="d-flex">
                        <input className="form-control me-2" type="text" placeholder="Search Your Nearest Center By Entering Your PINCODE" required value={pinCode} onChange={handlePinCodeChnage} min={100000} max={999999} inputMode='numeric' />
                        <button className="btn btn-outline-success" type="button" onClick={handleSearch}>Search</button>
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
