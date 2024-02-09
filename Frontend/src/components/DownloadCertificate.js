import moment from 'moment';

export default function DownloadCertificate() {
    function print() {
        let printContents = document.getElementById('printablediv').innerHTML;
        let originalContents = document.body.innerHTML;
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }
    return (

        <div className='container mb-5'>
            <div className="container border rounded w-50 p-3 mt-2">
                <div id='printablediv'>
                    <h1 className='text-center'>Covid Vaccination Certificate</h1>
                    <table class="table">
                        <tbody>
                            <tr><u><th className='text-decoration-underline p-2' colSpan={2}>Citizen Details</th></u></tr>
                            <tr>
                                <td>Citizen Name :</td>
                                <td>Manoj Lahu Hare</td>
                            </tr>
                            <tr>
                                <td>AadharID :</td>
                                <td>6162 6969 1234</td>
                            </tr>
                            <tr>
                                <td>Vaccination Status :</td>
                                <td>Partially Vaccinated (1 Dose)</td>
                            </tr>
                            <tr><u><th className='text-decoration-underline p-2' colSpan={2}>Vaccination Details</th></u></tr>
                            <tr>
                                <td>Vaccine Name :</td>
                                <td>Covishield</td>
                            </tr>
                            <tr>
                                <td>Date :</td>
                                <td>{moment(new Date()).format('LL')}</td>
                            </tr>
                            <tr>
                                <td>Time :</td>
                                <td>{moment(new Date()).format('LT')}</td>
                            </tr>

                            <tr>
                                <td>Center :</td>
                                <td>Nobel Hospital, Magarpatta, Hadapsar, Pune - 411007</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className='text-center'>
                    <button className='btn btn-primary' onClick={print} >Print</button>
                </div>

            </div>
        </div>
    )
}
