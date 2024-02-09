import React from 'react'

export default function CitizenDashboard() {
  return (
    <>
      <div className="container border rounded w-50 p-3 mt-5">
        <h1 className='text-center'>Welcome User</h1>
        <div className="card">
          <div className="card-header">
            <h3>Dose-1</h3>
          </div>
          <div className="card-body">
            <a href="/Home" className="btn btn-primary disabled me-2" aria-disabled="true">Book Slot</a>
            <a href="/CitizenServices/Dashboard/DownloadCertificate" className="btn btn-primary">Download Certificate</a>
          </div>
        </div>
      </div>
    </>

  )
}
