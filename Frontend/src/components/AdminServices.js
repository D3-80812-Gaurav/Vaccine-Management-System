import React from 'react'

export default function AdminServices() {

    return (
        <>
            <div className="container border rounded w-50 p-3 mt-5">
                <form>
                    <div className="form-group">
                        <label for="exampleInputEmail1">Enter CenterID</label>
                        <input type="number" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter CenterID Here" required />
                    </div>
                    <div className="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" className="form-control" id="exampleInputPassword1" placeholder="Enter Your Password" required />
                    </div>
                    <button type="submit" className="btn btn-primary mt-2 me-2">Submit</button>
                    <button type="reset" className="btn btn-primary mt-2">Reset</button>
                </form>
            </div>
        </>
    )
}