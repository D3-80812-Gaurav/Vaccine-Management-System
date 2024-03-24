import React, { useState } from 'react'
import Navbar from '../components/Navbar'
import SubNavbar from '../components/SubNavbar'
import axios from 'axios'

export default function ModifyCenter() {
    const handleSearch = () => {
        getCenterDetails();
    }
    const getCenterDetails = () => {
        const baseURL = process.env.REACT_APP_API_URL;
        const token = sessionStorage.getItem("vpAtoken");
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: baseURL + 'admin/get_center_details/' + centerId,
            headers: {
                'Authorization': 'Bearer ' + token
            }
        };

        axios.request(config)
            .then((response) => {
                const res = response.data;
                setId(res.id);
                setName(res.name);
                setState(res.state);
                setCity(res.city);
                setStock(res.stock);
                setPincode(res.pincode);
            })
            .catch((error) => {
                console.log("Nahi Aaya");
                setId();
                setName("");
                setState("");
                setCity("");
                setStock("");
                setPincode("");
            });
    }
    const updateCenterDetails = () => {
        const baseURL = process.env.REACT_APP_API_URL;
        const token = sessionStorage.getItem("vpAtoken");
        let data = JSON.stringify({ "id": id, "name": name, "state": state, "city": city, "pincode": pincode, "stock": stock });

        let config = {
            method: 'patch',
            maxBodyLength: Infinity,
            url: baseURL + 'admin/modify_center',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            data: data
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                setId("");
                setName("");
                setState("");
                setCity("");
                setStock("");
                setPincode("");
            })
            .catch((error) => {
                console.log(error);
            });
    }
    const handleSubmitAction = (e) => {
        e.preventDefault();
        updateCenterDetails();
    }

    const [centerId, setCenterId] = useState("");
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [state, setState] = useState("");
    const [city, setCity] = useState("");
    const [pincode, setPincode] = useState("");
    const [stock, setStock] = useState();
    return (
        <>
            <Navbar></Navbar>
            <SubNavbar></SubNavbar>
            <div className="container w-50">
                <div className="container text-center mt-2  ">
                    <form class="d-flex">
                        <input class="form-control me-2" type="text" placeholder="Enter Center ID:" required value={centerId} onChange={(e) => setCenterId(e.target.value)} />
                        <button class="btn btn-outline-success" type="button" onClick={handleSearch}>Search</button>
                    </form>
                </div>
                {(name.length == 0) &&
                    <div className="container text-center mt-2  text-danger">
                        <h3>{id}</h3>
                        <h5>Looks this Center Does Not Exist Search 🔍 for Another Center</h5>
                    </div>}
                {(name.length == 0) &&
                    (
                        <>
                            <div className="container border rounded mt-3">
                                <form>
                                    <input class="form-control" type="text" value={id} id='centerId' readonly hidden="true" />

                                    <label for="centerName" class="form-label mx-2">Center Name</label>
                                    <input class="form-control" type="text" value={name} id='centerName' onChange={(e) => setName(e.target.value)} />

                                    <label for="city" class="form-label mx-2">Center City</label>
                                    <input class="form-control" type="text" value={city} id='city' onChange={(e) => setCity(e.target.value)} />

                                    <label for="state" class="form-label mx-2">Center State</label>
                                    <input class="form-control" type="text" value={state} id='state' onChange={(e) => setState(e.target.value)} />

                                    <label for="pincode" class="form-label mx-2">Center Pincode</label>
                                    <input class="form-control" type="text" value={pincode} id='pincode' onChange={(e) => setPincode(e.target.value)} />

                                    <label for="stock" class="form-label mx-2">Center Vaccine Stock</label>
                                    <input class="form-control" type="number" value={stock} id='stock' onChange={(e) => setStock(e.target.value)} />

                                    <button className='btn btn-primary mt-2 mb-2' type='submit' onClick={handleSubmitAction}>Update Center Details</button>
                                </form>
                            </div >
                        </>
                    )}
            </div >
        </>

    )
}
