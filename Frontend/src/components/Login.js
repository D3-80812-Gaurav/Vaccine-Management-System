import React from 'react';
import { useState } from 'react';

export default function CitizenLogin() {
    function validateCredentials(event) {
        event.preventDefault();
        if ((aadhar == 123) && (password == 1234)) {
            window.location.href = "/CitizenServices/Dashboard";
        }
        else {
            document.getElementById("error_msg").innerText = "Invalid Credentials";
        }
    };
    function onAadharChange(event) {
        setAadhar(event.target.value);
    };
    function onPasswordChange(event) {
        setPassword(event.target.value);
    };
    const [aadhar, setAadhar] = useState();
    const [password, setPassword] = useState("");
    return (
        <div>
            <div className="container border rounded w-50 p-3 mt-5">
                <form>
                    <h1 className='text-center'>Citizen Login</h1>
                    <div className="form-group">
                        <label for="aadhar_id">Enter AadharID</label>
                        <input type="number" className="form-control" id="aadhar_id" placeholder="Enter AadharID" required value={aadhar} onChange={onAadharChange} />
                    </div>
                    <div className="form-group">
                        <label for="password">Password</label>
                        <input type="password" className="form-control" id="password" placeholder="Enter Your Password" required value={password} onChange={onPasswordChange} />
                    </div>
                    <div className='text-danger' id='error_msg'></div>
                    <button type="submit" className="btn btn-primary mt-2 me-2" onClick={validateCredentials}>Submit</button>
                    <button type="reset" className="btn btn-primary mt-2">Reset</button>
                </form>
            </div>
        </div>
    )
}
