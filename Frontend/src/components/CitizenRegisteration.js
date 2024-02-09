import React, { useState } from 'react';

export default function CitizenRegisteration() {

    function validateCredentials(event) {
        event.preventDefault();
        if ((aadhar >= 0) && (password == confirmPassword))
            window.location.href = "/CitizenServices/Login";

        else
            document.getElementById("error-msg").innerText = "Invalid AadharID or Passwords Didn't Match"
    };

    function onAadharChange(event) {
        setAadhar(event.target.value);
    };
    function onPasswordChange(event) {
        setPassword(event.target.value);
    }
    function onConfirmPasswordChange(event) {
        setConfirmPassword(event.target.value);
    }
    const [aadhar, setAadhar] = useState();
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    return (
        <div>
            <div className="container border rounded w-50 p-3 mt-5">
                <form>
                    <h1>New Citizen Registration</h1>
                    <div className="form-group">
                        <label for="aadhar_id">Enter AadharID</label>
                        <input type="number" className="form-control" id="aadhar_id" placeholder="Enter AadharID" required value={aadhar} onChange={onAadharChange} />
                    </div>
                    <div className="form-group">
                        <label for="password">Password</label>
                        <input type="password" className="form-control" id="password" placeholder="Enter Your Password" required value={password} onChange={onPasswordChange} />
                    </div>
                    <div className="form-group">
                        <label for="password_again">Confirm Password</label>
                        <input type="password" className="form-control" id="password_again" placeholder="Confirm Your Password" required value={confirmPassword} onChange={onConfirmPasswordChange} />
                    </div>
                    <div id='error-msg' className='text-danger'></div>
                    <button type="submit" className="btn btn-primary mt-2 me-2" onClick={validateCredentials}>Submit</button>
                    <button type="reset" className="btn btn-primary mt-2">Reset</button>
                </form>
            </div>
        </div>
    )
}


